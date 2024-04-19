package com.domain.employeeswipetracker.kafka;

import com.domain.employeeswipetracker.config.AttendanceEmployeeConfig;
import com.domain.employeeswipetracker.kafka.model.EmployeeAttendance;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AttendanceEmployeeProducer {

    private Logger log = LoggerFactory.getLogger(AttendanceEmployeeProducer.class);

    private KafkaTemplate<String, EmployeeAttendance> kafkaTemplate;

    private AttendanceEmployeeConfig attendanceEmployeeConfig;

    public AttendanceEmployeeProducer(KafkaTemplate<String, EmployeeAttendance> kafkaTemplate, AttendanceEmployeeConfig attendanceEmployeeConfig) {
        this.kafkaTemplate = kafkaTemplate;
        this.attendanceEmployeeConfig = attendanceEmployeeConfig;
    }

    public void sendEmployeeAttendance(EmployeeAttendance employeeAttendance) {
        log.info("Attendance of the {} sent to topic {} .", employeeAttendance, attendanceEmployeeConfig.getTopic());

        String employeeId = String.valueOf(employeeAttendance.getEmployeeId());
        CompletableFuture<SendResult<String, EmployeeAttendance>> resultCompletableFuture = kafkaTemplate.send("attendance_employee_topic", employeeId, employeeAttendance);
        resultCompletableFuture.whenComplete((stringEmployeeAttendanceSendResult, throwable) -> {
            if (throwable != null) {
                // handle failure
                log.info("Unable to send message=["
                        + employeeAttendance + "] due to : " + throwable.getMessage());
            } else {
                // handle success
                RecordMetadata attendanceSendResultRecordMetadata = stringEmployeeAttendanceSendResult.getRecordMetadata();
                log.info("Received new metadata \n" +
                        "Id: " + employeeId + "\n" +
                        "Partition: " + attendanceSendResultRecordMetadata.partition() + "\n" +
                        "Topic: " + attendanceSendResultRecordMetadata.topic() + "\n" +
                        "Offset: " + attendanceSendResultRecordMetadata.offset() + "\n" +
                        "Timestamp: " + attendanceSendResultRecordMetadata.timestamp());
            }
        });
    }
}
