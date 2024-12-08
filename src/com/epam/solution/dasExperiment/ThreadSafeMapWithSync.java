package com.epam.solution.dasExperiment;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The ConcurrentModificationException can occur in a multithreaded environment even when using synchronized blocks
 * because still the iterator will notice that the structure of the map (its internal data) has changed unexpectedly.
 */
public class ThreadSafeMapWithSync<K, V> implements Map<K, V> {

    private final Map<K, V> map;

    public ThreadSafeMapWithSync() {
        this.map = new HashMap<>();
    }

    @Override
    public synchronized int size() {
        return map.size();
    }

    @Override
    public synchronized boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public synchronized boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public synchronized boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public synchronized V get(Object key) {
        return map.get(key);
    }

    @Override
    public synchronized V put(K key, V value) {
        return map.put(key, value);
    }

    @Override
    public synchronized V remove(Object key) {
        return map.remove(key);
    }

    @Override
    public synchronized void putAll(Map<? extends K, ? extends V> putMap) {
        map.putAll(putMap);
    }

    @Override
    public synchronized void clear() {
        map.clear();
    }

    @Override
    public synchronized Set<K> keySet() {
        return map.keySet();
    }

    public synchronized Collection<V> values() {
        return map.values();
    }

    @Override
    public synchronized Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }
}
