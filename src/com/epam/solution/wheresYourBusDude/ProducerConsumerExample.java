package com.epam.solution.wheresYourBusDude;

public class ProducerConsumerExample {
    public static void main(String[] args) {
        CustomQueue buffer = new CustomQueue(10); // Shared buffer with a capacity of 10

        Thread producer1 = new Thread(new Producer(buffer), "Producer 1");
        Thread producer2 = new Thread(new Producer(buffer), "Producer 2");
        Thread producer3 = new Thread(new Producer(buffer), "Producer 3");

        Thread consumer1 = new Thread(new Consumer(buffer), "Consumer 1");
        Thread consumer2 = new Thread(new Consumer(buffer), "Consumer 2");
        Thread consumer3 = new Thread(new Consumer(buffer), "Consumer 3");

        producer1.start();
        consumer1.start();
        producer2.start();
        consumer2.start();
        producer3.start();
        consumer3.start();
    }
}
