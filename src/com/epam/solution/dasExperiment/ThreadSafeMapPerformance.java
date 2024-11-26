package com.epam.solution.dasExperiment;

import java.util.Map;

public class ThreadSafeMapPerformance {
    public static void main(String[] args) {
        Map<Integer, Integer> mapWithSync = new ThreadSafeMapWithSync<>();
        Map<Integer, Integer> mapWithoutSync = new ThreadSafeMapNoSync<>();

        // test mapWithSync write
        long test1Start = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            mapWithSync.put(i, i);
        }
        long test1Stop = System.currentTimeMillis();

        // test mapWithSync read
        long test2Start = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            mapWithSync.get(i);
        }
        long test2Stop = System.currentTimeMillis();

        // test mapWithoutSync write
        long test3Start = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            mapWithoutSync.put(i, i);
        }
        long test3Stop = System.currentTimeMillis();

        // test mapWithoutSync read
        long test4Start = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            mapWithoutSync.get(i);
        }
        long test4Stop = System.currentTimeMillis();

        System.out.printf("Map with sync write time(ms): %d\n", test1Stop - test1Start);
        System.out.printf("Map with sync read time(ms): %d\n", test2Stop - test2Start);
        System.out.printf("Map with reentrant lock write time(ms): %d\n", test3Stop - test3Start);
        System.out.printf("Map with reentrant lock read time(ms): %d\n", test4Stop - test4Start);
    }
}
