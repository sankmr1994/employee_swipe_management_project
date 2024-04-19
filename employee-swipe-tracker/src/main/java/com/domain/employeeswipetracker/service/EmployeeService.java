package com.domain.employeeswipetracker.service;

import com.domain.employeeswipetracker.dto.response.EmployeeRegisterResponseDto;
import com.domain.employeeswipetracker.model.Employee;

import java.util.List;

public interface EmployeeService {

    public EmployeeRegisterResponseDto registerEmployee(String name, String email);

    public List<EmployeeRegisterResponseDto> getAllEmployee();

    public EmployeeRegisterResponseDto getEmployee(Long employeeId);

    EmployeeRegisterResponseDto updateEmployee(Long employeeId, String name, String email);

    void deleteEmployee(Long employeeId);
}
