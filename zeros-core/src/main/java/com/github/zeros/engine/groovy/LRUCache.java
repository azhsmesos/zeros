package com.github.zeros.engine.groovy;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-10
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    public static final long serialVersionUID = -1L;

    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    private final int maxCapacity;

    public LRUCache(int maxCapacity) {
        super(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR, true);
        if (maxCapacity <= 0) {
            throw new IllegalArgumentException("Invalid maxCapacity: " + maxCapacity);
        }
        this.maxCapacity = maxCapacity;
    }

    public V remove(Object key) {
        return super.remove(key);
    }

    public int size() {
        return super.size();
    }

    public V put(K k, V v) {
        return super.put(k, v);
    }

    public V get(Object k) {
        return super.get(k);
    }

    protected boolean removeEldestEntry(Entry<K, V> eldest) {
        return this.size() > this.maxCapacity;
    }
}
