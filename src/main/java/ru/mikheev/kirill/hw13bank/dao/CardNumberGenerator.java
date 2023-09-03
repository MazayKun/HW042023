package ru.mikheev.kirill.hw13bank.dao;

import ru.mikheev.kirill.hw13bank.dao.general.Generator;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Генератор номеров карт
 */
public class CardNumberGenerator implements Generator<String> {

    private static final String SYSTEM_PREFIX = "1234 1234 1234 ";

    private final AtomicLong primalNumber = new AtomicLong();

    @Override
    public String next() {
        StringBuilder numberConstructor = new StringBuilder();
        long currentNumber = primalNumber.incrementAndGet();
        numberConstructor.setLength(0);
        numberConstructor.append(SYSTEM_PREFIX);
        if (currentNumber < 1000) numberConstructor.append(0);
        if (currentNumber < 100) numberConstructor.append(0);
        if (currentNumber < 10) numberConstructor.append(0);
        numberConstructor.append(currentNumber);
        return numberConstructor.toString();
    }
}
