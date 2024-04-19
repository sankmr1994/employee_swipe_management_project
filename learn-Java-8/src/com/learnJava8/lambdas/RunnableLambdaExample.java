package com.learnJava8.lambdas;

public class RunnableLambdaExample {

    public static void main(String[] args) {

        Runnable runnableLegacy = new Runnable() {
            @Override
            public void run() {
                System.out.println("runnableLegacy " + Thread.currentThread().getName());
            }
        };
        new Thread(runnableLegacy).start();

        //Using lambda
        Runnable runnableLambda1 = () -> System.out.println("runnableLambda1 " + Thread.currentThread().getName());
        new Thread(runnableLambda1).start();

        //We can directly pass functional method
        new Thread(() -> System.out.println("runnableLambda1 " + Thread.currentThread().getName())).start();
    }
}
