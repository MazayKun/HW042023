package ru.mikheev.kirill.hw33publisher.publishersubscriber.events;

import ru.mikheev.kirill.hw33publisher.collection.EventPublishingSupportedCollection;
import ru.mikheev.kirill.hw33publisher.publishersubscriber.CollectionEvent;

/**
 * Событие добавления нового элемента в коллекцию
 */
public class AddEvent extends CollectionEvent {

    /**
     * Новый элемент
     */
    private final Object addedElement;

    public AddEvent(EventPublishingSupportedCollection collection, Object addedElement) {
        super(collection);
        this.addedElement = addedElement;
    }

    @Override
    public String getEventTypeCode() {
        return "ADD";
    }

    @Override
    public String getEventContentAsString() {
        return addedElement.toString();
    }
}
