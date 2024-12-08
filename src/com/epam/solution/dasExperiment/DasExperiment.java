package com.epam.solution.dasExperiment;

import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class DasExperiment {
    public static void main(String[] args) {
        // Catches ConcurrentModificationException and exits
        Map<Integer, Integer> map = new HashMap<>();

        // Works fine, no ConcurrentModificationException
        // Map<Integer, Integer> map = new ConcurrentHashMap<>();

        // Catches ConcurrentModificationException and exits
        // Map<Integer, Integer> map = Collections.synchronizedMap(new HashMap<>());

        // Catches ConcurrentModificationException and exits (see ThreadSafeMapWithSync)
        // Map<Integer, Integer> map = new ThreadSafeMapWithSync<>();

        // Works fine, no ConcurrentModificationException
        // Map<Integer, Integer> map = new ThreadSafeMapNoSync<>();

        String firstThreadName = "Thread-addElements";
        String secondThreadName = "Thread-sumElements";

        Thread addElements = new Thread(() -> {
            try {
                for (int number = 0; number < 1000; number++) {
                    System.out.printf("%s: Adding %d to map\n", firstThreadName, number);
                    map.put(number, number);
                    System.out.printf("%s: %d added to map\n", firstThreadName, number);

                    Thread.sleep(10);
                }
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }, firstThreadName);

        Thread sumElements = new Thread(() -> {
            try {
                // Delay the 2nd thread start for it to have anything to be summed up
                Thread.sleep(1000);
                int currentMapSize = 0;

                // To avoid summing up infinitely
                while (currentMapSize != map.size()) {
                    currentMapSize = map.size();
                    int currentSum = map.values()
                            .stream()
                            .reduce(0, Integer::sum);

                    System.out.printf("%s: Current sum is %d\n", secondThreadName, currentSum);
                    Thread.sleep(500);
                }
            } catch (ConcurrentModificationException cme) {
                System.out.printf("%s caught ConcurrentModificationException, exiting..\n", secondThreadName);
                System.exit(1);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }

        }, secondThreadName);

        addElements.start();
        sumElements.start();
    }
}
