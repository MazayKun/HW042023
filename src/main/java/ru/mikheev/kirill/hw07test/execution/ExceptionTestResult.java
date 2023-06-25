package ru.mikheev.kirill.hw07test.execution;

import java.lang.reflect.Method;

public class ExceptionTestResult {
    private final TestExecutionStep step;
    private final Throwable occurredException;
    private final Method testMethod;

    private ExceptionTestResult(TestExecutionStep step, Throwable occurredException, Method testMethod) {
        this.step = step;
        this.occurredException = occurredException;
        this.testMethod = testMethod;
    }

    public static ExceptionTestResult beforeException(Throwable occurredException, Method testMethod) {
        return new ExceptionTestResult(TestExecutionStep.BEFORE, occurredException, testMethod);
    }

    public static ExceptionTestResult testException(Throwable occurredException, Method testMethod) {
        return new ExceptionTestResult(TestExecutionStep.TEST, occurredException, testMethod);
    }

    public static ExceptionTestResult afterException(Throwable occurredException, Method testMethod) {
        return new ExceptionTestResult(TestExecutionStep.AFTER, occurredException, testMethod);
    }

    @Override
    public String toString() {
        return String.format(
                "While executing test method %s, exception with message \"%s\" occurred during the %s",
                testMethod.getName(),
                occurredException.getMessage(),
                step.description
        );
    }
}
