package com.learnJava8.functionalInterfaces;

import com.learnJava8.data.Student;
import com.learnJava8.data.StudentDataBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class FunctionStudent {

    public static Function<List<Student>, Map<String, Integer>> function = students -> {
        Map<String, Integer> studentGradeMap = new HashMap<>();
        students.forEach(student -> {
            if (PredicateStudent.gradePredicate.test(student))
                studentGradeMap.put(student.getName(), student.getGradeLevel());
        });
        return studentGradeMap;
    };

    public static void main(String[] args) {
        System.out.println(function.apply(StudentDataBase.getAllStudents()));
    }
}
