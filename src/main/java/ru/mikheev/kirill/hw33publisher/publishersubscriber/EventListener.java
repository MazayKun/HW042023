package ru.mikheev.kirill.hw33publisher.publishersubscriber;

/**
 * ��������� �������
 *
 * @param <E> - ��� �������, ������� ������������ ������ ���������
 */
public interface EventListener<E extends CollectionEvent> {

    /**
     * ��������� ��������� � ����� �������
     *
     * @param event - �������, ������� ����� ����������
     */
    void notify(E event);
}
