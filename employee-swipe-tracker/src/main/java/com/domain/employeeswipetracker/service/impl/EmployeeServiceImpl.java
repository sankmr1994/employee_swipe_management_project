package com.domain.employeeswipetracker.service.impl;

import com.domain.employeeswipetracker.customException.EmployeeNotFoundException;
import com.domain.employeeswipetracker.dto.response.EmployeeRegisterResponseDto;
import com.domain.employeeswipetracker.model.Employee;
import com.domain.employeeswipetracker.repository.EmployeeRepository;
import com.domain.employeeswipetracker.service.EmployeeService;
import com.domain.employeeswipetracker.utils.EmployeeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeRegisterResponseDto> getAllEmployee() {
        List<Employee> employeeList = employeeRepository.findAll();
        return EmployeeUtils.mapEmployeeRegisterDtoList(employeeList);
    }

    @Override
    public EmployeeRegisterResponseDto getEmployee(Long employeeId) {
        return EmployeeUtils.mapEmployeeRegisterDto(findEmployee(employeeId));
    }

    @Override
    public EmployeeRegisterResponseDto registerEmployee(String name, String email) {
        Employee employee = new Employee(name, email);
        Employee savedEmployee = employeeRepository.save(employee);
        log.info("Employee register successfully...");
        return EmployeeUtils.mapEmployeeRegisterDto(savedEmployee);
    }

    @Override
    public EmployeeRegisterResponseDto updateEmployee(Long employeeId, String name, String email) {
        Employee employee = findEmployee(employeeId);
        Employee updateEmployee = new Employee(employee);
        Employee savedEmployee = employeeRepository.save(updateEmployee);
        log.info("Employee updated successfully...");
        return EmployeeUtils.mapEmployeeRegisterDto(savedEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isEmpty()) {
            throw new EmployeeNotFoundException(String.format("Employee not found with id : %d, Retry with valid id", employeeId));
        }
        employeeRepository.deleteById(employeeId);
    }

    public Employee findEmployee(Long employeeId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isEmpty()) {
            throw new EmployeeNotFoundException(String.format("Employee not found with id : %d, Retry with valid id", employeeId));
        }
        return optionalEmployee.get();
    }

}
