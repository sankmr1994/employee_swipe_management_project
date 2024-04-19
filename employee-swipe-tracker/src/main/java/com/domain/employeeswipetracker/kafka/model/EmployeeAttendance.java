package com.domain.employeeswipetracker.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeAttendance {

    private Long employeeId;

    private String name;

    private String email;

    private LocalDate date;

    private Long totalHour;

    private char attendanceStatus;

}
