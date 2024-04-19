package com.domain.employeeAttendancetracker.service.impl;

import com.domain.employeeAttendancetracker.model.EmployeeAttendanceEntity;
import com.domain.employeeAttendancetracker.repository.EmployeeAttendanceRepository;
import com.domain.employeeAttendancetracker.service.EmployeeAttendanceService;
import com.domain.employeeAttendancetracker.kafka.model.EmployeeAttendance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmployeeAttendanceServiceImpl implements EmployeeAttendanceService {

    private Logger log = LoggerFactory.getLogger(EmployeeAttendanceServiceImpl.class);

    private EmployeeAttendanceRepository employeeAttendanceRepository;

    public EmployeeAttendanceServiceImpl(EmployeeAttendanceRepository employeeAttendanceRepository) {
        this.employeeAttendanceRepository = employeeAttendanceRepository;
    }

    @Override
    public void saveEmployeeAttendance(EmployeeAttendance employeeAttendance) {
        EmployeeAttendanceEntity attendanceEntity = new EmployeeAttendanceEntity();
        attendanceEntity.setEmployeeId(employeeAttendance.getEmployeeId());
        attendanceEntity.setName(employeeAttendance.getName());
        attendanceEntity.setEmail(employeeAttendance.getEmail());
        attendanceEntity.setDate(employeeAttendance.getDate());
        attendanceEntity.setTotalHour(employeeAttendance.getTotalHour());
        attendanceEntity.setAttendanceStatus(employeeAttendance.getAttendanceStatus());

        employeeAttendanceRepository.save(attendanceEntity);
        log.info("Attendance of Employee {} saved of date {}", employeeAttendance.getName(), employeeAttendance.getDate());
    }
}
