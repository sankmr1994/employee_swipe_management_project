package com.learnJava8.functionalInterfaces;

import com.learnJava8.data.Student;

import java.util.Arrays;
import java.util.function.Supplier;

public class SupplierExample {

    public static void main(String[] args) {
        Supplier<Student> studentSupplier = () ->
                new Student("Sandy", 3, 3.7, "male",
                        10, Arrays.asList("swimming", "dancing", "football"));

        System.out.println(studentSupplier.get());


    }
}
