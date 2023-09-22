package ru.mikheev.kirill.hw33publisher.publishersubscriber.events;

import ru.mikheev.kirill.hw33publisher.publishersubscriber.CollectionEvent;
import ru.mikheev.kirill.hw33publisher.collection.EventPublishingSupportedCollection;

/**
 * ������� �������� �������� �� ���������
 */
public class RemoveEvent extends CollectionEvent {

    /**
     * ��������� �������
     */
    private final Object removeElement;

    public RemoveEvent(EventPublishingSupportedCollection collection, Object removeElement) {
        super(collection);
        this.removeElement = removeElement;
    }

    @Override
    public String getEventTypeCode() {
        return "REMOVE";
    }

    @Override
    public String getEventContentAsString() {
        return removeElement.toString();
    }
}
