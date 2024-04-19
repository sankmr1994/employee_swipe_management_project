package com.domain.employeeswipetracker.controllerExceptionAdvice;

import com.domain.employeeswipetracker.customException.EmployeeNotFoundException;
import com.domain.employeeswipetracker.customException.SwipeInNotValidException;
import com.domain.employeeswipetracker.dto.error.ErrorMsgDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerAdviceHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorMsgDto> employeeNotFoundException(EmployeeNotFoundException exception) {
        return new ResponseEntity<>(constructErrorMsg(exception), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SwipeInNotValidException.class)
    public ResponseEntity<ErrorMsgDto> swipeInNotValidException(SwipeInNotValidException exception) {
        return new ResponseEntity<>(constructErrorMsg(exception), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ErrorMsgDto constructErrorMsg(Exception exception) {
        ErrorMsgDto errorMsgDto = new ErrorMsgDto(exception.getMessage(), LocalDateTime.now());
        return errorMsgDto;
    }
}
