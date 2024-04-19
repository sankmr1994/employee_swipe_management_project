package com.learnJava8.functionalInterfaces;

import com.learnJava8.data.Student;
import com.learnJava8.data.StudentDataBase;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class PredicateAndConsumerExample {

    public static Predicate<Student> gradePredicate = student -> student.getGradeLevel() >= 3;
    public static Predicate<Student> gpaPredicate = student -> student.getGpa() >= 3.9;

    public static BiPredicate<Integer, Double> gradeAndGpaPredicate = (grade, gpa) -> grade >= 3 && gpa >= 3.9;

    public static BiConsumer<String, List<String>> nameAndActivitiesConsumer =
            (name, activities) -> System.out.println(name + "---" + activities);

    public static Consumer<Student> studentConsumer = student -> {
        if (gpaPredicate.and(gradePredicate).test(student)) {
            nameAndActivitiesConsumer.accept(student.getName(), student.getActivities());
        }
    };

    public static Consumer<Student> studentConsumerUsingBiPredicate = student -> {
        if (gradeAndGpaPredicate.test(student.getGradeLevel(), student.getGpa())) {
            nameAndActivitiesConsumer.accept(student.getName(), student.getActivities());
        }
    };

    private static void printNameAndActivities() {
        System.out.println("================= printNameAndActivities =================");
        StudentDataBase.getAllStudents().forEach(studentConsumer);
    }

    private static void printNameAndActivitiesUsingBiPredicate() {
        System.out.println("================= printNameAndActivitiesUsingBiPredicate =================");
        StudentDataBase.getAllStudents().forEach(studentConsumer);
    }


    public static void main(String[] args) {
        printNameAndActivities();
        printNameAndActivitiesUsingBiPredicate();
    }
}
