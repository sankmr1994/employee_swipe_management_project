package com.domain.employeeswipetracker.repository;

import com.domain.employeeswipetracker.model.Employee;
import com.domain.employeeswipetracker.model.SwipeInOut;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SwipeInOutRepository extends JpaRepository<SwipeInOut, Long> {
    SwipeInOut findFirstByDateAndEmployeeOrderByIdDesc(LocalDate now, Employee employee);

    List<SwipeInOut> findByDateAndEmployee(LocalDate now, Employee employee);

}
