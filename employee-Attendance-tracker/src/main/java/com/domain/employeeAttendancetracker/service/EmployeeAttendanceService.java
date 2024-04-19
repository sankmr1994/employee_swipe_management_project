package com.domain.employeeAttendancetracker.service;


import com.domain.employeeAttendancetracker.kafka.model.EmployeeAttendance;

public interface EmployeeAttendanceService {

    void saveEmployeeAttendance(EmployeeAttendance employeeAttendance);
}
