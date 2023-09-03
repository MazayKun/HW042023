package ru.mikheev.kirill.hw13bank.dao.general;

import ru.mikheev.kirill.hw13bank.exception.service.EntityAlreadyExistsException;
import ru.mikheev.kirill.hw13bank.exception.service.EntityNotFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * Хранилище объектов с базовыми операциями доступа к ним
 *
 * @param <K> - тип уникального ключы объекта
 * @param <V> - тип объекта
 */
public class CustomStorage<K, V extends Transactional> implements Transactional {

    private final String entityName; // Имя хранимых объектов

    private final Map<K, V> data = new HashMap<>();

    public CustomStorage(String entityName) {
        this.entityName = entityName;
    }

    /**
     * Получение объекта по ключу
     */
    public V get(K key) {
        return data.computeIfAbsent(key, stub -> {
            throw new EntityNotFoundException(entityName);
        });
    }

    /**
     * Удаление объекта по ключу
     *
     * @return Удаленный объект
     */
    public V remove(K key) {
        V value = data.remove(key);
        if (value == null) {
            throw new EntityNotFoundException(entityName);
        }
        return value;
    }

    /**
     * Добавление объекта с новым ключом
     *
     * @throws EntityAlreadyExistsException если объект с данным ключом уже есть в коллекции
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
