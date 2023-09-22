package ru.mikheev.kirill.hw33publisher.publishersubscriber;

import ru.mikheev.kirill.hw33publisher.collection.EventPublishingSupportedCollection;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * �������, ��������� � ����������
 */
public abstract class CollectionEvent {

    /**
     * ������ ���������� ������������� �������
     */
    private static final String FORMATTED_RESULT = "%s - Event with type %s on collection %s with content {%s}";

    /**
     * ������ ������������� ���� � ������� ��� ����������� ������� � ������
     */
    private static final DateTimeFormatter TIME_STAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * �����, ����� ���� ������� �������
     */
    private final LocalDateTime timeStamp;
    /**
     * ��� ���������, ��� ������� ����������� ��������
     */
    private final String collectionName;

    protected CollectionEvent(EventPublishingSupportedCollection collection) {
        this.timeStamp = LocalDateTime.now();
        this.collectionName = collection.getCollectionName();
    }

    /**
     * @return �����, ����� ���� ������� �������
     */
    public LocalDateTime getTimeStamp() {
        return this.timeStamp;
    }

    /**
     * @return �����, ����� ���� ������� �������, � ������� ������
     */
    public String getTimeStampString() {
        return TIME_STAMP_FORMATTER.format(this.timeStamp);
    }

    /**
     * @return ��� ���������, ��� ������� ����������� ��������
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
     * @return ��� ������� � ������� ������
     */
    protected abstract String getEventTypeCode();

    /**
     * @return ���������� ������� � ������� ������
     */
    public abstract String getEventContentAsString();
}
