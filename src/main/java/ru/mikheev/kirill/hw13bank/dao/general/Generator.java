package ru.mikheev.kirill.hw13bank.dao.general;

/**
 * Генерирует объекты заданного типа
 * @param <T> - тип генерируемых объектов
 */
public interface Generator<T> {

    T next();
}
