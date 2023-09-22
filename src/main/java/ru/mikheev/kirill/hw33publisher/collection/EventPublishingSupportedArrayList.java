package ru.mikheev.kirill.hw33publisher.collection;

import ru.mikheev.kirill.hw33publisher.publishersubscriber.CollectionOperationsEventPublisher;

import java.util.*;

/**
 * ArrayList realization, that publish events about array content change
 *
 * @param <T> - array elements type
 */
public class EventPublishingSupportedArrayList<T> extends EventPublishingSupportedCollection implements List<T> {

    private final List<T> list;
    private final CollectionOperationsEventPublisher eventPublisher;

    public EventPublishingSupportedArrayList(CollectionOperationsEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
        list = new ArrayList<>();
    }

    public EventPublishingSupportedArrayList(int initSize, CollectionOperationsEventPublisher eventPublisher) {
        list = new ArrayList<>(initSize);
        this.eventPublisher = eventPublisher;
    }

    public EventPublishingSupportedArrayList(Collection<? extends T> collection, CollectionOperationsEventPublisher eventPublisher) {
        list = new ArrayList<>(collection);
        this.eventPublisher = eventPublisher;
        for (var elem : list) {
            publishAdd(elem);
        }
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return list.listIterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return list.toArray(a);
    }

    @Override
    public boolean add(T t) {
        publishAdd(t);
        return list.add(t);
    }

    @Override
    public boolean remove(Object o) {
        publishRemove(o);
        return list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (var elem : c) {
            publishAdd(elem);
        }
        return list.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        for (var elem : c) {
            publishAdd(elem);
        }
        return list.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (var elem : c) {
            publishRemove(elem);
        }
        return list.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Object[] originalArray = list.toArray();
        boolean result = list.retainAll(c);
        if (result) {
            int i = 0;
            for (var elem : list) {
                while (i < originalArray.length && !originalArray[i].equals(elem)) {
                    publishRemove(originalArray[i]);
                    i++;
                }
                i++;
            }
            for (; i < originalArray.length; i++)
                publishRemove(originalArray[i]);
        }
        return result;
    }

    @Override
    public void clear() {
        for (var elem : list) {
            publishRemove(elem);
        }
        list.clear();
    }

    @Override
    public T get(int index) {
        return list.get(index);
    }

    @Override
    public T set(int index, T element) {
        publishAdd(element);
        T oldElem = list.set(index, element);
        if (oldElem != null) {
            publishRemove(oldElem);
        }
        return oldElem;
    }

    @Override
    public void add(int index, T element) {
        publishAdd(element);
        list.add(index, element);
    }

    @Override
    public T remove(int index) {
        T elem = list.remove(index);
        publishRemove(elem);
        return elem;
    }

    @Override
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return list.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return list.listIterator(index);
    }

    @Override
    public EventPublishingSupportedArrayList<T> subList(int fromIndex, int toIndex) {
        List<T> subList = list.subList(fromIndex, toIndex);
        return new EventPublishingSupportedArrayList<>(subList, eventPublisher);
    }

    @Override
    String getCollectionType() {
        return "EventPublishingSupportedArrayList";
    }

    private void publishAdd(Object elem) {
        eventPublisher.publishAdd(this, elem);
    }

    private void publishRemove(Object elem) {
        eventPublisher.publishRemove(this, elem);
    }
}
