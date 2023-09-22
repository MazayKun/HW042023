package ru.mikheev.kirill.hw33publisher;

import ru.mikheev.kirill.hw33publisher.collection.EventPublishingSupportedArrayList;
import ru.mikheev.kirill.hw33publisher.publishersubscriber.CollectionOperationsEventPublisher;
import ru.mikheev.kirill.hw33publisher.publishersubscriber.listeners.AddConsoleListener;
import ru.mikheev.kirill.hw33publisher.publishersubscriber.listeners.AddFileListener;
import ru.mikheev.kirill.hw33publisher.publishersubscriber.listeners.RemoveConsoleListener;
import ru.mikheev.kirill.hw33publisher.publishersubscriber.listeners.RemoveFileListener;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        CollectionOperationsEventPublisher eventPublisher = new CollectionOperationsEventPublisher();
        eventPublisher.subscribeAddEventListener(new AddFileListener("./ADD_LOG"));
        eventPublisher.subscribeRemoveEventListener(new RemoveFileListener("./REMOVE_LOG"));
        eventPublisher.subscribeAddEventListener(new AddConsoleListener());
        eventPublisher.subscribeRemoveEventListener(new RemoveConsoleListener());
        EventPublishingSupportedArrayList<String> arrayListWithEvents = new EventPublishingSupportedArrayList<>(eventPublisher);

        arrayListWithEvents.add("White");
        arrayListWithEvents.add("Purple");
        arrayListWithEvents.add("Black");
        arrayListWithEvents.add("Red");
        arrayListWithEvents.add("Grey");

        List<String> supportList = new ArrayList<>();

        supportList.add("Green");
        supportList.add("Red");
        supportList.add("White");

        arrayListWithEvents.retainAll(supportList);
        arrayListWithEvents.remove("Red");

        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
