package com.domain.employeeAttendancetracker.repository;

import com.domain.employeeAttendancetracker.model.EmployeeAttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendanceEntity, Long> {
}
