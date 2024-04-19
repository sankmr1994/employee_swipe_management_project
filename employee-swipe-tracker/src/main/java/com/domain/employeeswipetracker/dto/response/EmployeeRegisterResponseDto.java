package com.domain.employeeswipetracker.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRegisterResponseDto {
    private Long id;
    private String name;
    private String email;
}
