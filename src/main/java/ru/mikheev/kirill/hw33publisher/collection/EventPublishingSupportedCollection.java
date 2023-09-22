package ru.mikheev.kirill.hw33publisher.collection;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ���������, ������� ������������ ���������� ������� �� ��������� ������ ���������
 */
public abstract class EventPublishingSupportedCollection {

    /**
     * ������� ��������� ���������
     * ����� ��� ������������ ����� ���������
     */
    private static final AtomicInteger INSTANCE_COUNTER = new AtomicInteger();

    /**
     * ��� ���������� ���������
     */
    private final String collectionName;

    protected EventPublishingSupportedCollection() {
        this.collectionName = getCollectionType() + " " + INSTANCE_COUNTER.incrementAndGet();
    }

    /**
     * @return ��������� �������� ��������� ��� ������������ �����
     */
    abstract String getCollectionType();

    /**
     * @return ��� ����������� ���������� ���������
     */
    public String getCollectionName() {
        return collectionName;
    }
}
