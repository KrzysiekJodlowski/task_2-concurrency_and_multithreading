package com.epam.solution.simpleObjectPool;

public class BlockingObjectPoolExample {
    public static void main(String[] args) throws InterruptedException {
        var poolCapacity = 10;
        var workerCount = 5;
        var workerActions = 20;
        var pool = new BlockingObjectPool(poolCapacity);

        // Simulating a multithreaded environment
        for (int i = 1; i <= workerCount; i++) {
            Thread worker = new Thread(new Worker(pool, workerActions), "Worker-" + i);
            worker.start();
        }
    }
}

class Worker implements Runnable {
    private final BlockingObjectPool pool;
    private final int actionCount;

    public Worker(BlockingObjectPool pool, int actionCount) {
        this.pool = pool;
        this.actionCount = actionCount;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < actionCount; i++) {
                Object obj = pool.get();
                System.out.printf("%s: acquired the object %s from the pool\n",
                        Thread.currentThread().getName(), obj.toString().replaceAll(".*@", "")
                );

                Thread.sleep((long) (Math.random() * 1000));

                pool.take(obj);
                System.out.printf("%s: released the object %s back to the pool\n",
                        Thread.currentThread().getName(), obj.toString().replaceAll(".*@", "")
                );
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
