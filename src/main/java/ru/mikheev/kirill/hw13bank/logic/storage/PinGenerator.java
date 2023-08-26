package ru.mikheev.kirill.hw13bank.logic.storage;

import java.util.Random;

public class PinGenerator implements Generator<String> {

    private final StringBuilder numberConstructor = new StringBuilder();
    private final Random random = new Random();

    @Override
    public String next() {
        numberConstructor.setLength(0);
        for (int i = 0; i < 4; i++) {
            numberConstructor.append(random.nextInt(10));
        }
        return numberConstructor.toString();
    }
}
