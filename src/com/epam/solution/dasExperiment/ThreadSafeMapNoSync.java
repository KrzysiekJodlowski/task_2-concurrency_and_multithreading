package com.epam.solution.dasExperiment;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Thread-safety using the ReentrantLock, an alternative is e.g. to use the Semaphore.
 */
public class ThreadSafeMapNoSync<K, V> implements Map<K, V> {

    private Map<K, V> map;
    private final Lock lock;

    public ThreadSafeMapNoSync() {
        map = new HashMap<>();
        lock = new ReentrantLock();
    }

    @Override
    public V put(K key, V value) {
        lock.lock();
        try {
            Map<K, V> newMap = new HashMap<>(map);
            newMap.put(key, value);
            map = newMap;
        } finally {
            lock.unlock();
        }
        return value;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        lock.lock();
        try {
            Map<K, V> newMap = new HashMap<>(map);
            newMap.putAll(m);
            map = newMap;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public V remove(Object key) {
        lock.lock();
        try {
            Map<K, V> newMap = new HashMap<>(map);
            V value = newMap.remove(key);
            map = newMap;
            return value;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public V get(Object key) {
        lock.lock();
        try {
            return map.get(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean containsKey(Object key) {
        lock.lock();
        try {
            return map.containsKey(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int size() {
        lock.lock();
        try {
            return map.size();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        lock.lock();
        try {
            return map.isEmpty();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean containsValue(Object value) {
        lock.lock();
        try {
            return map.containsValue(value);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void clear() {
        lock.lock();
        try {
            map.clear();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Set<K> keySet() {
        lock.lock();
        try {
            return map.keySet();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Collection<V> values() {
        lock.lock();
        try {
            return map.values();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        lock.lock();
        try {
            return map.entrySet();
        } finally {
            lock.unlock();
        }
    }
}
