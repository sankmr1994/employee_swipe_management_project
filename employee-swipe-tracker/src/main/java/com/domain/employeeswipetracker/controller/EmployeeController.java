package com.domain.employeeswipetracker.controller;

import com.domain.employeeswipetracker.dto.request.EmployeeRegisterRequestDto;
import com.domain.employeeswipetracker.dto.request.EmployeeUpdateRequestDto;
import com.domain.employeeswipetracker.dto.response.EmployeeRegisterResponseDto;
import com.domain.employeeswipetracker.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/v1/employees")
    public List<EmployeeRegisterResponseDto> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @GetMapping("/v1/employees/{id}")
    public EmployeeRegisterResponseDto getEmployee(@PathVariable("id") Long employeeId) {
        return employeeService.getEmployee(employeeId);
    }

    @PostMapping("/v1/employees")
    public ResponseEntity<EmployeeRegisterResponseDto> registerEmployee(@RequestBody EmployeeRegisterRequestDto employee) {
        log.info("Employee to be register {} ", employee);
        EmployeeRegisterResponseDto registeredEmployee = employeeService.registerEmployee(employee.getName(), employee.getEmail());
        return new ResponseEntity<>(registeredEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/v1/employees/{id}")
    public ResponseEntity<EmployeeRegisterResponseDto> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeUpdateRequestDto employeeUpdateRequest) {
        EmployeeRegisterResponseDto updatedEmployee = employeeService.updateEmployee(employeeId, employeeUpdateRequest.getName(), employeeUpdateRequest.getEmail());
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/v1/employees/{id}")
    public void deleteEmployee(@PathVariable("id") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
    }


}
