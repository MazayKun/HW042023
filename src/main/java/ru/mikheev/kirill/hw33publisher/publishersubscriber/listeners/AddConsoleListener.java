package ru.mikheev.kirill.hw33publisher.publishersubscriber.listeners;

import ru.mikheev.kirill.hw33publisher.publishersubscriber.EventListener;
import ru.mikheev.kirill.hw33publisher.publishersubscriber.events.AddEvent;

/**
 * Print events about adding new elements to collection in console
 */
public class AddConsoleListener implements EventListener<AddEvent> {

    @Override
    public void notify(AddEvent event) {
        System.out.println(event.toString());
    }
}
