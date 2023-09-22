package ru.mikheev.kirill.hw33publisher.publishersubscriber.listeners;

import ru.mikheev.kirill.hw33publisher.publishersubscriber.EventListener;
import ru.mikheev.kirill.hw33publisher.publishersubscriber.events.RemoveEvent;

/**
 * ������� � ������� ������� �������� ��������� �� ���������
 */
public class RemoveConsoleListener implements EventListener<RemoveEvent> {

    @Override
    public void notify(RemoveEvent event) {
        System.out.println(event.toString());
    }
}
