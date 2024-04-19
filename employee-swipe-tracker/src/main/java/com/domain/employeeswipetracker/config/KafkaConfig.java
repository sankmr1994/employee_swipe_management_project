package com.domain.employeeswipetracker.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    private AttendanceEmployeeConfig attendanceEmployeeConfig;

    public KafkaConfig(AttendanceEmployeeConfig attendanceEmployeeConfig) {
        this.attendanceEmployeeConfig = attendanceEmployeeConfig;
    }

    @Bean
    public NewTopic attendanceEmployeeTopic() {
        return TopicBuilder.name(attendanceEmployeeConfig.getTopic()).build();
    }

}
