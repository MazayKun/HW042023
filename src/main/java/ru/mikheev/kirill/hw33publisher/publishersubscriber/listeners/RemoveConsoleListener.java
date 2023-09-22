package ru.mikheev.kirill.hw33publisher.publishersubscriber.listeners;

import ru.mikheev.kirill.hw33publisher.publishersubscriber.EventListener;
import ru.mikheev.kirill.hw33publisher.publishersubscriber.events.RemoveEvent;

/**
 * Выводит в консоль события удаления элементов из коллекции
 */
public class RemoveConsoleListener implements EventListener<RemoveEvent> {

    @Override
    public void notify(RemoveEvent event) {
        System.out.println(event.toString());
    }
}
