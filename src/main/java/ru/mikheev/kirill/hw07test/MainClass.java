package ru.mikheev.kirill.hw07test;

import ru.mikheev.kirill.hw07test.execution.TestUtils;

public class MainClass {

    public static void main(String[] args) {
        TestUtils.performTestScenarios(UsageExample.class, UsageExample.class);
    }
}
