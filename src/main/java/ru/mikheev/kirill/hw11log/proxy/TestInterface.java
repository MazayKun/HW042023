package ru.mikheev.kirill.hw11log.proxy;

import ru.mikheev.kirill.hw11log.proxy.instrument.Log;

public interface TestInterface {

    @Log
    int emptyMethod();

    @Log
    int arrayMethod(int[] test, int a);

    @Log
    Double sqr(Double a);

    @Log
    Integer sum(Integer a, Integer b);

    @Log
    Object ifMethod(Boolean a, Object thenBranch, Object elseBranch);
}
