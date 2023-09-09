package ru.mikheev.kirill.hw13bank.dao.general;

/**
 * Generate objects of specified type
 * @param <T> - type of generated objects
 */
public interface Generator<T> {

    T next();
}
