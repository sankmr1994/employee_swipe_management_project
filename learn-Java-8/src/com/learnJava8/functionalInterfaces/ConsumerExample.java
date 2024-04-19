package com.learnJava8.functionalInterfaces;

import com.learnJava8.data.Student;
import com.learnJava8.data.StudentDataBase;

import java.util.function.Consumer;

public class ConsumerExample {

    static Consumer<Student> studentConsumer = student -> System.out.println(student);
    static Consumer<Student> studentNameConsumer = student -> System.out.println(student.getName());
    static Consumer<Student> studentActivityConsumer = student -> System.out.println(student.getActivities());

    private static void printStudents() {
        System.out.println("================= printStudents =================");
        StudentDataBase.getAllStudents().forEach(studentConsumer);
    }

    private static void printStudentNames() {
        System.out.println("================= printStudentNames =================");
        StudentDataBase.getAllStudents().forEach(studentNameConsumer);
    }

    private static void printStudentNameAndActivities() {
        System.out.println("================= printStudentNameAndActivities =================");
        StudentDataBase.getAllStudents().forEach(studentNameConsumer.andThen(studentActivityConsumer));
    }

    private static void printStudentNameAndActivitiesUsingCondition() {
        System.out.println("================= printStudentNameAndActivitiesUsingCondition =================");
        StudentDataBase.getAllStudents().forEach(student -> {
            if (student.getGradeLevel() >= 3 && student.getGpa() >= 3.9) {
                studentNameConsumer.andThen(studentActivityConsumer).accept(student);
            }
        });
    }

    public static void main(String[] args) {
        Consumer<String> stringConsumer = s -> System.out.println(s.toUpperCase());
        stringConsumer.accept("Sandy");
        printStudents();
        printStudentNames();
        printStudentNameAndActivities();
        printStudentNameAndActivitiesUsingCondition();
    }

}
