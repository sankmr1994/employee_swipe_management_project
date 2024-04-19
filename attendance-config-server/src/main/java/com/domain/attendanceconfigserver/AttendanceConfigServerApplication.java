package com.domain.attendanceconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class AttendanceConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AttendanceConfigServerApplication.class, args);
    }

}
