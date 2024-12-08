package com.epam.solution.simpleObjectPool;

import java.util.LinkedList;

/**
 * A ready to be used and simpler solution would be to use simply a BlockingQueue<Object>
 * but a more challenging version was chosen for the sake of the exercise.
 */
public class BlockingObjectPool {

    private final LinkedList<Object> pool;
    private final int maxSize;

    /**
     * Creates a filled pool of passed size with default objects.
     *
     * @param size the size of the pool
     */
    public BlockingObjectPool(int size) {
        this.pool = new LinkedList<>();
        this.maxSize = size;

        // Initialize the pool with default objects
        for (int i = 0; i < size; i++) {
            pool.add(new Object());
        }
    }

    /**
     * Gets an object from the pool or blocks if the pool is empty.
     *
     * @return object from the pool
     */
    public synchronized Object get() {
        while (pool.isEmpty()) {
            try {
                wait(); // Wait until an object is available
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore the interrupted status
                throw new RuntimeException("Thread interrupted while waiting for an object", e);
            }
        }

        Object obj = pool.removeFirst(); // Retrieve the first object
        notifyAll(); // Notify any waiting threads that space is available
        return obj;
    }

    /**
     * Puts an object back into the pool or blocks if the pool is full.
     *
     * @param object the object to be taken back into the pool
     */
    public synchronized void take(Object object) {
        while (pool.size() == maxSize) {
            try {
                wait(); // Wait until space is available
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore the interrupted status
                throw new RuntimeException("Thread interrupted while waiting to return an object", e);
            }
        }

        pool.addLast(object); // Add the object back to the pool
        notifyAll(); // Notify any waiting threads that an object is available
    }
}
