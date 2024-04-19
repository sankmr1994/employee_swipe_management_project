package com.learnJava8.functionalInterfaces;

import com.learnJava8.data.Student;
import com.learnJava8.data.StudentDataBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class BiFunctionExample {

    static BiFunction<List<Student>, Predicate<Student>, Map<String, Integer>> biFunction = (students, studentPredicate) -> {
        Map<String, Integer> res = new HashMap<>();

        students.forEach(student -> {
            if (studentPredicate.test(student)) {
                res.put(student.getName(), student.getGradeLevel());
            }
        });
        return res;
    };

    public static void main(String[] args) {
        System.out.println(biFunction.apply(StudentDataBase.getAllStudents(), PredicateStudent.gradePredicate));
    }
}
