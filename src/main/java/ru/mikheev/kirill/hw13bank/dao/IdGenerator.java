package ru.mikheev.kirill.hw13bank.dao;

import ru.mikheev.kirill.hw13bank.dao.general.Generator;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Long id generator
 */
public class IdGenerator implements Generator<Long> {

    private final AtomicLong counter = new AtomicLong();

    public Long next() {
        return counter.incrementAndGet();
    }
}
