package ru.mikheev.kirill.hw13bank.logic.container;

public interface Transactional {

    void fixState();

    void commitState();

    void rollbackState();
}
