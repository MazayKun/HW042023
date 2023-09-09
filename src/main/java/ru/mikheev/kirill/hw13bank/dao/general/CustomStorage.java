package ru.mikheev.kirill.hw13bank.dao.general;

import ru.mikheev.kirill.hw13bank.exception.service.EntityAlreadyExistsException;
import ru.mikheev.kirill.hw13bank.exception.service.EntityNotFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * In memory objects storage with basic access operation
 *
 * @param <K> - type of unique object id
 * @param <V> - type of object
 */
public class CustomStorage<K, V extends Transactional> implements Transactional {

    private final String entityName; // Stored entity name

    private final Map<K, V> data = new HashMap<>();

    public CustomStorage(String entityName) {
        this.entityName = entityName;
    }

    /**
     * Get object by id
     */
    public V get(K key) {
        return data.computeIfAbsent(key, stub -> {
            throw new EntityNotFoundException(entityName);
        });
    }

    /**
     * Remove object by id
     *
     * @return Removed object
     */
    public V remove(K key) {
        V value = data.remove(key);
        if (value == null) {
            throw new EntityNotFoundException(entityName);
        }
        return value;
    }

    /**
     * Add new object
     *
     * @throws EntityAlreadyExistsException if object with the same key already exists in storage
     */
    public void add(K key, V value) {
        if (data.putIfAbsent(key, value) != null) {
            throw new EntityAlreadyExistsException(entityName);
        }
    }

    @Override
    public void fixState() {
        for (V value : data.values()) {
            value.fixState();
        }
    }

    @Override
    public void commitState() {
        for (V value : data.values()) {
            value.commitState();
        }
    }

    @Override
    public void rollbackState() {
        for (V value : data.values()) {
            value.rollbackState();
        }
    }
}
