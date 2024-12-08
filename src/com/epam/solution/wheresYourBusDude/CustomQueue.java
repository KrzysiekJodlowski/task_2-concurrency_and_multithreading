package com.epam.solution.wheresYourBusDude;

import java.util.LinkedList;
import java.util.Queue;

public class CustomQueue {

    private final Queue<String> queue = new LinkedList<>();
    private final int capacity;

    public CustomQueue(int capacity) {
        this.capacity = capacity;
    }

    // Producer adding an item to the queue
    public synchronized void put(String message) throws InterruptedException {
        while (queue.size() == capacity) {
            wait(); // Wait if the queue is full
        }
        queue.add(message);
        notifyAll(); // Notify consumers that an item is available
    }

    // Consumer retrieving an item from the queue
    public synchronized String take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait(); // Wait if the queue is empty
        }
        String message = queue.poll();
        notifyAll(); // Notify producers that queue space is available
        return message;
    }
}
