package com.domain.employeeswipetracker.service.impl;

import com.domain.employeeswipetracker.customException.SwipeInNotValidException;
import com.domain.employeeswipetracker.kafka.AttendanceEmployeeProducer;
import com.domain.employeeswipetracker.kafka.model.EmployeeAttendance;
import com.domain.employeeswipetracker.model.Employee;
import com.domain.employeeswipetracker.model.SwipeInOut;
import com.domain.employeeswipetracker.repository.EmployeeRepository;
import com.domain.employeeswipetracker.repository.SwipeInOutRepository;
import com.domain.employeeswipetracker.service.SwipeInOutService;
import com.domain.employeeswipetracker.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class SwipeInOutServiceImpl implements SwipeInOutService {

    private final Logger log = LoggerFactory.getLogger(SwipeInOutServiceImpl.class);

    private SwipeInOutRepository swipeInOutRepository;

    private EmployeeServiceImpl employeeService;

    private EmployeeRepository employeeRepository;

    public SwipeInOutServiceImpl(SwipeInOutRepository swipeInOutRepository, EmployeeServiceImpl employeeService, EmployeeRepository employeeRepository) {
        this.swipeInOutRepository = swipeInOutRepository;
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public String swipeInOut(Long employeeId, String swipeState) {
        SwipeInOut swipeInOut = validate(employeeId, swipeState);
        if (CommonUtils.SWIPE_IN.equalsIgnoreCase(swipeState)) {
            swipeInOut.setSwipeIn(LocalTime.now());
        } else {
            swipeInOut.setSwipeOut(LocalTime.now());
        }
        swipeInOutRepository.save(swipeInOut);
        log.info("{} Swiped {} Successfully...", swipeInOut.getEmployee().getName(), swipeState.toUpperCase());
        return String.format("%s Swiped %s Successfully.", swipeInOut.getEmployee().getName(), swipeState.toUpperCase());
    }

    public SwipeInOut validate(Long employeeId, String swipeState) {
        Employee employee = employeeService.findEmployee(employeeId);
        validateSwipeInOut(employee, swipeState);
        SwipeInOut swipeInOut = new SwipeInOut();
        swipeInOut.setEmployee(employee);
        swipeInOut.setDate(LocalDate.now());
        return swipeInOut;
    }

    private void validateSwipeInOut(Employee employee, String swipeState) {
        SwipeInOut lastSwipeRecord = swipeInOutRepository.findFirstByDateAndEmployeeOrderByIdDesc(LocalDate.now(), employee);
        if (lastSwipeRecord == null && swipeState.equalsIgnoreCase(CommonUtils.SWIPE_OUT)) {
            throw new SwipeInNotValidException("Cannot Swipe-Out Without Swipe-In at first time.");
        }
        if (lastSwipeRecord != null) {
            if (lastSwipeRecord.getSwipeIn() != null && swipeState.equalsIgnoreCase(CommonUtils.SWIPE_IN)) {
                throw new SwipeInNotValidException("You already Swiped-In. Swipe-Out to Swipe-In.");
            }
            if (lastSwipeRecord.getSwipeOut() != null && swipeState.equalsIgnoreCase(CommonUtils.SWIPE_OUT)) {
                throw new SwipeInNotValidException("You already Swiped-Out. Swipe-In to Swipe-Out.");
            }
        }
    }

    @Override
    public List<EmployeeAttendance> calculateEmployeeTotalHours() {
        List<EmployeeAttendance> employeeAttendanceList = new ArrayList<>();
        List<Employee> employeeList = employeeRepository.findAll();
        employeeList.stream().forEach(employee -> {
            List<SwipeInOut> swipeInOut = swipeInOutRepository.findByDateAndEmployee(LocalDate.now(), employee);
            if (swipeInOut.size() > 0) {
                long totalHourSpend = calculatePerDayTotalHour(swipeInOut);
                EmployeeAttendance employeeAttendance = mapEmployeeAttendance(employee, swipeInOut.get(0).getDate(), totalHourSpend);
                employeeAttendanceList.add(employeeAttendance);
            }
        });
        return employeeAttendanceList;
    }

    private Long calculatePerDayTotalHour(List<SwipeInOut> swipeInOutList) {
        int size = swipeInOutList.size();
        LocalTime swipeInPointer = swipeInOutList.get(0).getSwipeIn();
        LocalTime swipeOutPointer = null;
        long totalSecondsSpend = 0;
        for (int index = 1; index < size; index++) {
            LocalTime swipeIn = swipeInOutList.get(index).getSwipeIn();
            LocalTime swipeOut = swipeInOutList.get(index).getSwipeOut();
            if (swipeInPointer == null && swipeIn != null) {
                swipeInPointer = swipeIn;
            }
            if (swipeOut != null) {
                swipeOutPointer = swipeOut;
                long seconds = ChronoUnit.SECONDS.between(swipeInPointer, swipeOutPointer);
                totalSecondsSpend = totalSecondsSpend + seconds;
                swipeInPointer = null;
                log.info("Seconds employee spend outside {} ", seconds);
            }
        }
        long totalHourSpend = TimeUnit.SECONDS.toHours(totalSecondsSpend);
        return totalHourSpend;
    }

    private EmployeeAttendance mapEmployeeAttendance(Employee employee, LocalDate date, long totalHourSpend) {
        EmployeeAttendance employeeAttendance = new EmployeeAttendance();
        employeeAttendance.setEmployeeId(employee.getId());
        employeeAttendance.setName(employee.getName());
        employeeAttendance.setEmail(employee.getEmail());
        employeeAttendance.setDate(date);
        employeeAttendance.setTotalHour(totalHourSpend);
        if (totalHourSpend < 4) {
            employeeAttendance.setAttendanceStatus('A');
        } else if (totalHourSpend > 4 && totalHourSpend < 8) {
            employeeAttendance.setAttendanceStatus('H');
        } else {
            employeeAttendance.setAttendanceStatus('P');
        }
        return employeeAttendance;
    }
}
