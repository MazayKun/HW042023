package ru.mikheev.kirill.hw33publisher.publishersubscriber.listeners;

import ru.mikheev.kirill.hw33publisher.publishersubscriber.EventListener;
import ru.mikheev.kirill.hw33publisher.publishersubscriber.events.AddEvent;

/**
 * Выводит события о добавлении элементов в коллекцию в консоль
 */
public class AddConsoleListener implements EventListener<AddEvent> {

    @Override
    public void notify(AddEvent event) {
        System.out.println(event.toString());
    }
}
