package com.domain.employeeAttendancetracker.kafka;

import com.domain.employeeAttendancetracker.config.AttendanceEmployeeConfig;
import com.domain.employeeAttendancetracker.kafka.model.EmployeeAttendance;
import com.domain.employeeAttendancetracker.service.impl.EmployeeAttendanceServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class AttendanceEmployeeConsumer {


    private final Logger log = LoggerFactory.getLogger(AttendanceEmployeeConsumer.class);

    private EmployeeAttendanceServiceImpl employeeAttendanceService;

    private AttendanceEmployeeConfig attendanceEmployeeConfig;


    public AttendanceEmployeeConsumer(EmployeeAttendanceServiceImpl employeeAttendanceService, AttendanceEmployeeConfig attendanceEmployeeConfig) {
        this.employeeAttendanceService = employeeAttendanceService;
        this.attendanceEmployeeConfig = attendanceEmployeeConfig;
    }

    @KafkaListener(topics = "#{'${consumer.topic.name}'}", groupId = "employeeAttendanceGroup")
    public void consumeEmployeeAttendance(EmployeeAttendance employeeAttendance) {
        log.info("Received {}'s Attendance of date {} ", employeeAttendance.getName(), employeeAttendance.getDate());
        employeeAttendanceService.saveEmployeeAttendance(employeeAttendance);
    }
}
