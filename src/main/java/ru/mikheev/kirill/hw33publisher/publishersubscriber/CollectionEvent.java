package ru.mikheev.kirill.hw33publisher.publishersubscriber;

import ru.mikheev.kirill.hw33publisher.collection.EventPublishingSupportedCollection;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event connected with collection
 */
public abstract class CollectionEvent {

    /**
     * String format for event
     */
    private static final String FORMATTED_RESULT = "%s - Event with type %s on collection %s with content {%s}";

    /**
     * String format for time stamp
     */
    private static final DateTimeFormatter TIME_STAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Event creation time
     */
    private final LocalDateTime timeStamp;

    /**
     * Name of collection on which operation was performed
     */
    private final String collectionName;

    protected CollectionEvent(EventPublishingSupportedCollection collection) {
        this.timeStamp = LocalDateTime.now();
        this.collectionName = collection.getCollectionName();
    }

    /**
     * @return event creation time
     */
    public LocalDateTime getTimeStamp() {
        return this.timeStamp;
    }

    /**
     * @return event creation time as a string
     */
    public String getTimeStampString() {
        return TIME_STAMP_FORMATTER.format(this.timeStamp);
    }

    /**
     * @return name of collection on which operation was performed
     */
    public String getCollectionName() {
        return collectionName;
    }

    @Override
    public String toString() {
        return String.format(
                FORMATTED_RESULT,
                getTimeStampString(),
                getEventTypeCode(),
                getCollectionName(),
                getEventContentAsString()
        );
    }

    /**
     * @return event type as a string
     */
    protected abstract String getEventTypeCode();

    /**
     * @return event content as a string
     */
    public abstract String getEventContentAsString();
}
