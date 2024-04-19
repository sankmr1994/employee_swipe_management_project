package com.domain.employeeswipetracker.customException;

public class SwipeInNotValidException extends RuntimeException {

    public SwipeInNotValidException(String errorMsg) {
        super(errorMsg);
    }
}
