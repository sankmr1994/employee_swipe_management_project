package com.learnJava8.functionalInterfaces;

import com.learnJava8.data.StudentDataBase;

import java.util.List;
import java.util.function.BiConsumer;

public class BiConsumerExample {

    public static BiConsumer<String, List<String>> biConsumer = (name, activities) -> System.out.println(name + " : " + activities);


    private static void nameAndActivities() {
        StudentDataBase.getAllStudents().forEach(student -> biConsumer.accept(student.getName(), student.getActivities()));
    }

    public static void main(String[] args) {
        BiConsumer<String, String> stringBiConsumer = (s1, s2) -> System.out.println(s1 + "--" + s2);
        stringBiConsumer.accept("sandy", "kmr");

        BiConsumer<Integer, Integer> multiple = (a, b) -> System.out.println("Multiple : " + (a * b));
        multiple.accept(10, 5);
        BiConsumer<Integer, Integer> divide = (a, b) -> System.out.println("Divide : " + (a / b));
        divide.accept(10, 5);

        multiple.andThen(divide).accept(10, 5);

        System.out.println("=============================");

        nameAndActivities();
    }
}
