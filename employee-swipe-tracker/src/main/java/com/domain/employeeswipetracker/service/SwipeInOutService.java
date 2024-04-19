package com.domain.employeeswipetracker.service;

import com.domain.employeeswipetracker.kafka.model.EmployeeAttendance;

import java.util.List;

public interface SwipeInOutService {

    public String swipeInOut(Long employeeId, String swipeState);

    List<EmployeeAttendance> calculateEmployeeTotalHours();
}
