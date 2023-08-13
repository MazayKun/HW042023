package ru.mikheev.kirill.hw11log.proxy;

import ru.mikheev.kirill.hw11log.proxy.instrument.ObjectFactory;

public class ProxyTest {

    public static void main(String[] args) {
        TestInterface object = ObjectFactory.createInstance(TestInterface.class, TestClass.class, "start");
        System.out.println("empty - " + object.emptyMethod());
        System.out.println("smart array - " + object.arrayMethod(new int[]{1,2,3,4}, 2));
        System.out.println("sqr 3.0 = " + object.sqr(3.0));
        System.out.println("1 + 2 = " + object.sum(1, 2));
        System.out.println("true ? \"hello\" : \"bye\" - " + object.ifMethod(Boolean.TRUE, "hello", "bye"));
    }
}
