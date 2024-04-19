package com.learnJava8.functionalInterfaces;

import java.util.function.Predicate;

public class PredicateExample {

    public static Predicate<Integer> isDivideByTwo = n -> n % 2 == 0;
    public static Predicate<Integer> isDivideByFive = n -> n % 5 == 0;

    private static void predicateAnd(){
        System.out.println("Predicate AND result: " + isDivideByTwo.and(isDivideByFive).test(10));
        System.out.println("Predicate AND result: " + isDivideByTwo.and(isDivideByFive).test(5));
    }
    private static void predicateOr(){
        System.out.println("Predicate OR result: " + isDivideByTwo.or(isDivideByFive).test(10));
        System.out.println("Predicate OR result: " + isDivideByTwo.or(isDivideByFive).test(5));
    }
    private static void predicateNegate(){
        System.out.println("Predicate Negate result: " + isDivideByTwo.or(isDivideByFive).negate().test(10));
    }

    public static void main(String[] args) {
        Predicate<Integer> isDivideByTwo = n -> n % 2 == 0;
        System.out.println("is Divide By Two : " + isDivideByTwo.test(4));
        System.out.println("is Divide By Two : " + isDivideByTwo.test(5));
        predicateAnd();
        predicateOr();
        predicateNegate();
    }
}
