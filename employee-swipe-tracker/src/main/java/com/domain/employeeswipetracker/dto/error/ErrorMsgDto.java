package com.domain.employeeswipetracker.dto.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMsgDto {
    private String error;

    private LocalDateTime dateTime;
}
