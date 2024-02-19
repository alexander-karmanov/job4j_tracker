package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {
    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    public final void put(K key, V value) {
        SoftReference<V> softReference = new SoftReference(value);
        cache.put(key, softReference);
    }

    public final V get(K key) {
        V mapValue = cache.getOrDefault(key, new SoftReference<V>(null)).get();
        if (mapValue == null) {
            mapValue = load(key);
            put(key, mapValue);
        }
        return mapValue;
    }

    protected abstract V load(K key);
}
