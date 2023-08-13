package ru.mikheev.kirill.hw11log.proxy;

public class TestClass implements TestInterface {

    public TestClass(String str) {
        System.out.println(str);
    }

    @Override
    public int emptyMethod() {
        return 0;
    }

    @Override
    public int arrayMethod(int[] test, int a) {
        int result = 0;
        for(int elem : test) {
            result += elem * a;
        }
        return result;
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
