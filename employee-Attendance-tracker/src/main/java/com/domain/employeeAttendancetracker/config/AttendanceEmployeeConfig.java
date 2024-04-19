package com.domain.employeeAttendancetracker.config;

import com.domain.employeeAttendancetracker.kafka.AttendanceEmployeeConsumer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties("attendance-employee")
public class AttendanceEmployeeConfig {
    private String topic;

}
