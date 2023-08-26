package ru.mikheev.kirill.hw13bank.logic.storage;

public class IdGenerator implements Generator<Long> {
    private long counter = 0;

    public Long next() {
        return ++counter;
    }
}
