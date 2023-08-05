package ru.mikheev.kirill.hw11log.proxy;

public class TestClass implements TestInterface {

    public TestClass(String str) {
        System.out.println(str);
    }

    @Override
    public Double sqr(Double a) {
        return a * a;
    }

    @Override
    public Integer sum(Integer a, Integer b) {
        return a + b;
    }

    @Override
    public Object ifMethod(Boolean a, Object thenBranch, Object elseBranch) {
        return a ? thenBranch : elseBranch;
    }
}
