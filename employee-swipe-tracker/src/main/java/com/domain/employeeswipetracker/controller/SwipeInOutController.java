package com.domain.employeeswipetracker.controller;


import com.domain.employeeswipetracker.config.AttendanceEmployeeConfig;
import com.domain.employeeswipetracker.dto.request.SwipeInOutRequest;
import com.domain.employeeswipetracker.kafka.AttendanceEmployeeProducer;
import com.domain.employeeswipetracker.kafka.model.EmployeeAttendance;
import com.domain.employeeswipetracker.service.impl.SwipeInOutServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SwipeInOutController {

    private final Logger log = LoggerFactory.getLogger(SwipeInOutController.class);

    private SwipeInOutServiceImpl swipeInOutService;

    private AttendanceEmployeeProducer attendanceEmployeeProducer;

    private AttendanceEmployeeConfig attendanceEmployeeConfig;

    public SwipeInOutController(SwipeInOutServiceImpl swipeInOutService, AttendanceEmployeeProducer attendanceEmployeeProducer, AttendanceEmployeeConfig attendanceEmployeeConfig) {
        this.swipeInOutService = swipeInOutService;
        this.attendanceEmployeeProducer = attendanceEmployeeProducer;
        this.attendanceEmployeeConfig = attendanceEmployeeConfig;
    }

    @PostMapping("/v1/swipe/employee")
    public ResponseEntity<String> swipeInOut(@RequestBody SwipeInOutRequest swipeInOutRequest) {
        String swipeResult = swipeInOutService.swipeInOut(swipeInOutRequest.getEmployeeId(), swipeInOutRequest.getSwipeState());
        return new ResponseEntity<>(swipeResult, HttpStatus.OK);
    }

    @Scheduled(cron = "0 0 12 * * ?")
    @PostMapping("/v1/swipe/employee/attendance")
    public ResponseEntity<String> calculateEmployeeTotalHours() {
        log.info("Calculate total hours of all employees");
        List<EmployeeAttendance> employeeAttendanceList = swipeInOutService.calculateEmployeeTotalHours();
        if (employeeAttendanceList.size() > 0) {
            log.info("Start sending employee attendance to the topic {}.", attendanceEmployeeConfig.getTopic());
            for (EmployeeAttendance employeeAttendance : employeeAttendanceList)
                attendanceEmployeeProducer.sendEmployeeAttendance(employeeAttendance);
            log.info("End sending employee attendance to the topic {}.",attendanceEmployeeConfig.getTopic());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
