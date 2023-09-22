package ru.mikheev.kirill.hw33publisher.publishersubscriber;

/**
 * Слушатель событий
 *
 * @param <E> - тип событий, которые обрабатывает данный слушатель
 */
public interface EventListener<E extends CollectionEvent> {

    /**
     * Уведомить слушателя о новом событии
     *
     * @param event - событие, которое нужно обработать
     */
    void notify(E event);
}
