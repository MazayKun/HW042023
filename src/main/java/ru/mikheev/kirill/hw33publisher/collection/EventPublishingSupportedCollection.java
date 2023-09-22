package ru.mikheev.kirill.hw33publisher.collection;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Collection, that supports event publishing about content change
 */
public abstract class EventPublishingSupportedCollection {

    /**
     *
     * Created collections counter
     * Required for collection instance name generation
     */
    private static final AtomicInteger INSTANCE_COUNTER = new AtomicInteger();

    /**
     * Collection instance name
     */
    private final String collectionName;

    protected EventPublishingSupportedCollection() {
        this.collectionName = getCollectionType() + " " + INSTANCE_COUNTER.incrementAndGet();
    }

    /**
     * @return collection type as a string
     */
    abstract String getCollectionType();

    /**
     * @return collection instance name
     */
    public String getCollectionName() {
        return collectionName;
    }
}
