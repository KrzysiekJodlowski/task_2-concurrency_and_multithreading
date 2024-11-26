package com.epam.solution.wheresYourBusDude;

import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {

    private final CustomQueue queue;
    private final AtomicInteger messageCounter = new AtomicInteger(0);

    public Producer(CustomQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                var messagePattern = "Message no %d";
                queue.put(String.format(messagePattern, messageCounter.addAndGet(1)));
                Thread.sleep(500); // Pretending that producing the message takes longer
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
