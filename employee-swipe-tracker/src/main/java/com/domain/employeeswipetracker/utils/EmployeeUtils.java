package com.domain.employeeswipetracker.utils;

import com.domain.employeeswipetracker.dto.response.EmployeeRegisterResponseDto;
import com.domain.employeeswipetracker.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeUtils {

    public static EmployeeRegisterResponseDto mapEmployeeRegisterDto(Employee employee) {
        return new EmployeeRegisterResponseDto(employee.getId(), employee.getName(), employee.getEmail());
    }

    public static List<EmployeeRegisterResponseDto> mapEmployeeRegisterDtoList(List<Employee> employeeList) {
        List<EmployeeRegisterResponseDto> employeeRegisterList = employeeList.stream().map(employee ->
                new EmployeeRegisterResponseDto(employee.getId(), employee.getName(), employee.getEmail())
        ).collect(Collectors.toList());
        return employeeRegisterList;
    }
}
