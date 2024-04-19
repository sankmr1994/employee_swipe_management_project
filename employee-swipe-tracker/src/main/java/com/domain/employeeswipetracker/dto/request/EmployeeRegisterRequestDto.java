package com.domain.employeeswipetracker.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeRegisterRequestDto {

    private String name;

    private String email;
}
