package ru.mikheev.kirill.hw33publisher.publishersubscriber.events;

import ru.mikheev.kirill.hw33publisher.collection.EventPublishingSupportedCollection;
import ru.mikheev.kirill.hw33publisher.publishersubscriber.CollectionEvent;

/**
 * Adding new element to collection event
 */
public class AddEvent extends CollectionEvent {

    /**
     * New element
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
