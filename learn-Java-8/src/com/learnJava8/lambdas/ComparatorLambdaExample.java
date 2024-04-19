package com.learnJava8.lambdas;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ComparatorLambdaExample {

    public static void main(String[] args) {

        List<Integer> nums = Arrays.asList(1, 3, 2, 6, 5);
        System.out.println("Before Sort " + nums);

        // Before Java 8
        Comparator<Integer> comparatorLegacy = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -o1.compareTo(o2);
            }
        };
        nums.sort(comparatorLegacy);
        System.out.println("After Sort " + nums);

        //Re-initialize
        nums = Arrays.asList(1, 3, 2, 6, 5);

        // Using Lambda
        Comparator<Integer> comparatorLambda = (a,b) -> -a.compareTo(b);
        nums.sort(comparatorLambda);
        System.out.println("After Sort " + nums);

    }
}
