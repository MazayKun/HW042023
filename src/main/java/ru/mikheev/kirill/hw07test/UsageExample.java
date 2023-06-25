package ru.mikheev.kirill.hw07test;

import ru.mikheev.kirill.hw07test.annotation.After;
import ru.mikheev.kirill.hw07test.annotation.Before;
import ru.mikheev.kirill.hw07test.annotation.Test;

public class UsageExample {

    @Before
    public void firstBefore() {
        System.out.println("First before method was executed");
    }

    @Before
    public void secondBefore() {
        System.out.println("Second before method was executed");
    }

    @Test
    public void firstTest() {
        if(true) throw new RuntimeException("Message");
        System.out.println("First test method was executed");
    }

    @Test
    public void secondTest() {
        System.out.println("Second test method was executed");
    }

    @After
    public void firstAfter() {
        System.out.println("First after method was executed");
    }

    @After
    public void secondAfter() {
        System.out.println("Second after method was executed");
    }
}
