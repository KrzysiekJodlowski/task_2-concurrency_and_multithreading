package com.epam.solution.deadlocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Calculations {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        var random = new Random();
        var lock = new Object();

        Thread writeNumbers = new Thread(() -> {
            // non-short-circuit operation used in the Lambda expression
            // is a bad practice but here used for sake of simplicity
            random.ints(1, 1000)
                    .forEach(number -> {
                        synchronized(lock) {
                            numbers.add(number);
                        }
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                    });

        }, "writeNumbers");

        Thread printSum = new Thread(() -> {
            try {
                // delay start
                Thread.sleep(1000);

                while (true) {
                    int sum;

                    synchronized (lock) {
                        sum = numbers.stream().reduce(0, Integer::sum);
                    }
                    System.out.printf("Sum of the numbers: %d\n", sum);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }, "printSum");

        Thread printSquareRoot = new Thread(() -> {
            try {
                // delay start even more
                Thread.sleep(2000);
                while (true) {
                    double squareRoot;

                    synchronized (lock) {
                        var sumOfSquares = numbers.stream()
                                .mapToInt(number -> number * number)
                                .reduce(0, Integer::sum);
                        squareRoot = Math.sqrt(sumOfSquares);
                    }
                    System.out.printf("Square root of sum of squares: %.0f\n", squareRoot);
                    Thread.sleep(2000);
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }, "printSquareRoot");

        writeNumbers.start();
        printSum.start();
        printSquareRoot.start();
    }
}
