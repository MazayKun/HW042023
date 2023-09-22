package ru.mikheev.kirill.hw33publisher.publishersubscriber;

import ru.mikheev.kirill.hw33publisher.collection.EventPublishingSupportedCollection;
import ru.mikheev.kirill.hw33publisher.publishersubscriber.events.AddEvent;
import ru.mikheev.kirill.hw33publisher.publishersubscriber.events.RemoveEvent;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ������������ ��� ���������� ������� �� ���������, ���������� ��� �����������
 */
public class CollectionOperationsEventPublisher {

    private final List<EventListener<RemoveEvent>> removeEventListeners = new CopyOnWriteArrayList<>();
    private final List<EventListener<AddEvent>> addEventListeners = new CopyOnWriteArrayList<>();

    private final BlockingQueue<CollectionEvent> eventsQueue = new LinkedBlockingDeque<>();

    private final Lock workerLock = new ReentrantLock();
    private final Condition newEventAvailableCondition = workerLock.newCondition();
    private Thread workerThread;

    public CollectionOperationsEventPublisher() {
        startPublisher();
    }

    /**
     * ���������� ������� � ���, ��� ������� ��� ������ �� ���������
     *
     * @param collection - ���������, ��� ������� ����������� ��������
     * @param removedObject - ��������� �������
     */
    public void publishRemove(EventPublishingSupportedCollection collection, Object removedObject) {
        eventsQueue.add(new RemoveEvent(collection, removedObject));
        newEventSignal();
    }

    /**
     * ���������� ������� � ���, ��� ������� ��� �������� � ���������
     *
     * @param collection - ���������, ��� ������� ����������� ��������
     * @param addedObject - ����������� �������
     */
    public void publishAdd(EventPublishingSupportedCollection collection, Object addedObject) {
        eventsQueue.add(new AddEvent(collection, addedObject));
        newEventSignal();
    }

    /**
     * ��������� ����� ����� ��������� �������
     * @throws RuntimeException ���� ��� ������� ����� ��������� �������
     */
    public void startPublisher() {
        if(workerThread != null) throw new RuntimeException("Worker thread already exists");
        this.workerThread = new Thread(() -> {
            try {
                while (Thread.currentThread() == workerThread) {
                    if(!eventsQueue.isEmpty()) processAllAvailableEvents();
                    workerLock.lock();
                    newEventAvailableCondition.await(500L, TimeUnit.MILLISECONDS);
                    workerLock.unlock();
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        this.workerThread.setDaemon(true);
        this.workerThread.start();
    }

    /**
     * ������������� ����� ��������� �������
     */
    public void stopPublisher() {
        workerThread = null;
    }

    /**
     * ����������� ����� ���������� ������� �� �������� �������� �� ���������
     * @param newListener - ����������, ������� ����� ���������
     */
    public void subscribeRemoveEventListener(EventListener<RemoveEvent> newListener) {
        removeEventListeners.add(newListener);
    }

    /**
     * ����������� ����� ���������� ������� � ���������� ������ �������� � ���������
     * @param newListener - ����������, ������� ����� ���������
     */
    public void subscribeAddEventListener(EventListener<AddEvent> newListener) {
        addEventListeners.add(newListener);
    }

    /**
     * ������������� ������ ��������� ������� � ���, ��� ��������� ����� �������
     */
    private void newEventSignal() {
        workerLock.lock();
        newEventAvailableCondition.signalAll();
        workerLock.unlock();
    }

    /**
     * ������������ ��� ��������� �� ������ ������ �������
     */
    private void processAllAvailableEvents() {
        CollectionEvent event;
        while((event = eventsQueue.poll()) != null) {
            if(event instanceof AddEvent addEvent) {
                for(var listener : addEventListeners) {
                    listener.notify(addEvent);
                }
            }

            if(event instanceof RemoveEvent removeEvent) {
                for(var listener : removeEventListeners) {
                    listener.notify(removeEvent);
                }
            }
        }
    }
}
