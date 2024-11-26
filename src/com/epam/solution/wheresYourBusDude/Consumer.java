package com.epam.solution.wheresYourBusDude;

public class Consumer implements Runnable {

    private final CustomQueue queue;

    public Consumer(CustomQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                var message = queue.take();
                System.out.println("Consumed: " + message);
                Thread.sleep(1000); // Pretending that consuming the message takes longer
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}