package ru.mikheev.kirill.hw13bank.dao;

import ru.mikheev.kirill.hw13bank.dao.general.Generator;

import java.util.Random;

/**
 * Генератор пин кодов для карт
 */
public class PinGenerator implements Generator<String> {

    private final Random random = new Random();

    @Override
    public String next() {
        StringBuilder numberConstructor = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            numberConstructor.append(random.nextInt(10));
        }
        return numberConstructor.toString();
    }
}
