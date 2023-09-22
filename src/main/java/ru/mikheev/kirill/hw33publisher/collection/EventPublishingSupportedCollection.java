package ru.mikheev.kirill.hw33publisher.collection;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Коллекция, которая поддерживает публикацию событий об изменении своего состояния
 */
public abstract class EventPublishingSupportedCollection {

    /**
     * Счетчик созданных коллекций
     * Нужен для формирования имени коллекции
     */
    private static final AtomicInteger INSTANCE_COUNTER = new AtomicInteger();

    /**
     * Имя конкретной коллекции
     */
    private final String collectionName;

    protected EventPublishingSupportedCollection() {
        this.collectionName = getCollectionType() + " " + INSTANCE_COUNTER.incrementAndGet();
    }

    /**
     * @return строковое название коллекции для формирования имени
     */
    abstract String getCollectionType();

    /**
     * @return имя конкретного экземпляра коллекции
     */
    public String getCollectionName() {
        return collectionName;
    }
}
