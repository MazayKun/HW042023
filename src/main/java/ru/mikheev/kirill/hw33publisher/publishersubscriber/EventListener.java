package ru.mikheev.kirill.hw33publisher.publishersubscriber;

/**
 * @param <E> - event type, which handled by listener
 */
public interface EventListener<E extends CollectionEvent> {

    /**
     * Notify listener about new event
     *
     * @param event - event to handle
     */
    void notify(E event);
}
