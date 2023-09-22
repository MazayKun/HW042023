package ru.mikheev.kirill.hw33publisher.publishersubscriber;

import ru.mikheev.kirill.hw33publisher.collection.EventPublishingSupportedCollection;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Событие, связанное с коллекцией
 */
public abstract class CollectionEvent {

    /**
     * Формат строкового представления события
     */
    private static final String FORMATTED_RESULT = "%s - Event with type %s on collection %s with content {%s}";

    /**
     * Формат представления даты и времени при конвертации события в строку
     */
    private static final DateTimeFormatter TIME_STAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Время, когда было создано событие
     */
    private final LocalDateTime timeStamp;
    /**
     * Имя коллекции, над которой проводилась операция
     */
    private final String collectionName;

    protected CollectionEvent(EventPublishingSupportedCollection collection) {
        this.timeStamp = LocalDateTime.now();
        this.collectionName = collection.getCollectionName();
    }

    /**
     * @return Время, когда было создано событие
     */
    public LocalDateTime getTimeStamp() {
        return this.timeStamp;
    }

    /**
     * @return Время, когда было создано событие, в формате строки
     */
    public String getTimeStampString() {
        return TIME_STAMP_FORMATTER.format(this.timeStamp);
    }

    /**
     * @return Имя коллекции, над которой проводилась операция
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
     * @return Тип события в формате строки
     */
    protected abstract String getEventTypeCode();

    /**
     * @return Наполнение события в формате строки
     */
    public abstract String getEventContentAsString();
}
