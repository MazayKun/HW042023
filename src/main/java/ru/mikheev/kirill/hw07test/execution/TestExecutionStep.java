package ru.mikheev.kirill.hw07test.execution;

public enum TestExecutionStep {
    BEFORE("Data preparation phase"),
    TEST("Test method execution phase"),
    AFTER("After test cleaning phase");

    final String description;

    TestExecutionStep(String description) {
        this.description = description;
    }
}
