package com.learnJava8.methodReferences;

import com.learnJava8.data.Student;
import com.learnJava8.data.StudentDataBase;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ConsumerMethodReference {


    public static void main(String[] args) {
        Consumer<String> stringConsumer = System.out::println;

        Consumer<Student> studentConsumer = Student::printListOfActivities;

        stringConsumer.accept("sandy");
        StudentDataBase.getAllStudents().forEach(studentConsumer);

        List<List<String>> lists = StudentDataBase.getAllStudents().stream().map(Student::getActivities).collect(Collectors.toList());


    }
}
