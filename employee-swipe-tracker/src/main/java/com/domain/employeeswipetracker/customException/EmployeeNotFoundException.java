package com.domain.employeeswipetracker.customException;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(String errorMsg) {
        super(errorMsg);
    }
}
