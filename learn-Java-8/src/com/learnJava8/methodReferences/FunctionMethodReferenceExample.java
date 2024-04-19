package com.learnJava8.methodReferences;

import java.util.function.Function;

public class FunctionMethodReferenceExample {

    public static void main(String[] args) {

        Function<String, String> function = s -> s.toUpperCase();

        Function<String,String> methodRef = String::toUpperCase;

        System.out.println(function.apply("sandy"));

    }
}
