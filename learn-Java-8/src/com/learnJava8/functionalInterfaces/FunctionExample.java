package com.learnJava8.functionalInterfaces;

import java.util.function.Function;

public class FunctionExample {

    public static Function<String, String> convertToUppercaseFunction = name -> name.toUpperCase();

    public static Function<String, String> concatFunction = name -> name.concat(" Kumar");

    public static void main(String[] args) {
        System.out.println(convertToUppercaseFunction.apply("sandy"));

        System.out.println(convertToUppercaseFunction.andThen(concatFunction).apply("sandy"));

        System.out.println(convertToUppercaseFunction.compose(concatFunction).apply("sandy"));
    }
}
