package com.learnJava8.methodReferences;

import com.learnJava8.data.Student;

import java.util.function.Predicate;

public class RefactorMethodReference {


    public Predicate<Student> studentPredicate = new RefactorMethodReference()::greaterThenGradeLevel;

    public boolean greaterThenGradeLevel(Student student) {
        return student.getGradeLevel() >= 3;
    }

    public static void main(String[] args) {

    }
}
