package ru.mikheev.kirill.hw13bank.logic.storage;

import ru.mikheev.kirill.hw13bank.logic.container.Transactional;
import ru.mikheev.kirill.hw13bank.util.exception.service.EntityAlreadyExistsException;
import ru.mikheev.kirill.hw13bank.util.exception.service.EntityNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class CustomStorage<K, V extends Transactional> implements Transactional {

    private final String entityName;

    private final Map<K, V> data = new HashMap<>();
    private final Map<K, V> prevState = new HashMap<>();

    private boolean stateChanged;

    public CustomStorage(String entityName) {
        this.entityName = entityName;
    }

    public V get(K key) {
        stateChanged = true;
        return data.computeIfAbsent(key, stub -> {
            throw new EntityNotFoundException(entityName);
        });
    }

    public V remove(K key) {
        stateChanged = true;
        V value = data.remove(key);
        if(value == null) {
            throw new EntityNotFoundException(entityName);
        }
        return value;
    }

    public void add(K key, V value) {
        stateChanged = true;
        if (data.putIfAbsent(key, value) != null) {
            throw new EntityAlreadyExistsException(entityName);
        }
    }

    @Override
    public void fixState() {
        for(V value : data.values()) {
            value.fixState();
        }
    }

    @Override
    public void commitState() {
        for(V value : data.values()) {
            value.commitState();
        }
    }

    @Override
    public void rollbackState() {
        for(V value : data.values()) {
            value.rollbackState();
        }
    }
}
