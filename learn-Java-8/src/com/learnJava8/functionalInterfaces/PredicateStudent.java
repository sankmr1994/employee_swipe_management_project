package com.learnJava8.functionalInterfaces;

import com.learnJava8.data.Student;
import com.learnJava8.data.StudentDataBase;

import java.util.function.Predicate;

public class PredicateStudent {

    public static Predicate<Student> gradePredicate = student -> student.getGradeLevel() >= 3;
    public static Predicate<Student> gpaPredicate = student -> student.getGpa() >= 3.9;

    public static void filterStudentByGrade() {
        System.out.println("================= filterStudentByGrade =================");
        StudentDataBase.getAllStudents().forEach(student -> {
            if (gradePredicate.test(student)) {
                System.out.println(student);
            }
        });
    }

    public static void filterStudentByGpa() {
        System.out.println("================= filterStudentByGpa =================");
        StudentDataBase.getAllStudents().forEach(student -> {
            if (gpaPredicate.test(student)) {
                System.out.println(student);
            }
        });
    }

    public static void filterStudentByGpaAndGrade() {
        System.out.println("================= filterStudentByGpaAndGrade =================");
        StudentDataBase.getAllStudents().forEach(student -> {
            if (gpaPredicate.and(gradePredicate).test(student)) {
                System.out.println(student);
            }
        });
    }

    public static void filterStudentByNotGpaAndGrade() {
        System.out.println("================= filterStudentByNotGpaAndGrade =================");
        StudentDataBase.getAllStudents().forEach(student -> {
            if (gpaPredicate.or(gradePredicate).negate().test(student)) {
                System.out.println(student);
            }
        });
    }


    public static void main(String[] args) {
        filterStudentByGrade();
        filterStudentByGpa();
        filterStudentByGpaAndGrade();
        filterStudentByNotGpaAndGrade();
    }
}
