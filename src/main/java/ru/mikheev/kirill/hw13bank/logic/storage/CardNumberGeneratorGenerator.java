package ru.mikheev.kirill.hw13bank.logic.storage;

public class CardNumberGeneratorGenerator implements Generator<String> {

    private static final String SYSTEM_PREFIX = "1234 1234 1234 ";
    private final StringBuilder numberConstructor = new StringBuilder();

    private long primalNumber = 0;

    @Override
    public String next() {
        primalNumber++;
        numberConstructor.setLength(0);
        numberConstructor.append(SYSTEM_PREFIX);
        if (primalNumber < 1000) numberConstructor.append(0);
        if (primalNumber < 100) numberConstructor.append(0);
        if (primalNumber < 10) numberConstructor.append(0);
        numberConstructor.append(primalNumber);
        return numberConstructor.toString();
    }
}
