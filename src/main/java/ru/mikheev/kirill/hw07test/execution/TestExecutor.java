package ru.mikheev.kirill.hw07test.execution;

import ru.mikheev.kirill.hw07test.annotation.After;
import ru.mikheev.kirill.hw07test.annotation.Before;
import ru.mikheev.kirill.hw07test.annotation.Test;
import ru.mikheev.kirill.hw07test.exception.EntityCreationException;
import ru.mikheev.kirill.hw07test.exception.WrongAnnotationUsageException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

class TestExecutor {

    private Class<?> testScenariosHolder;

    private List<Method> beforeMethods;
    private List<Method> testMethods;
    private List<Method> afterMethods;

    private Constructor<?> noArgsConstructor;

    private int testMethodsNumber;
    private List<ExceptionTestResult> failedTestResultList;

    TestExecutor() {}

    InitStep initTestScenariosHolder(Class<?> testScenariosHolder) {
        System.out.println(String.format("Start %s test scenarios execution", testScenariosHolder.getSimpleName()));
        this.testScenariosHolder = testScenariosHolder;
        return new InitStep();
    }

    private void prepareMethods() {
        Method[] allMethods = testScenariosHolder.getMethods();
        testMethodsNumber = 0;
        beforeMethods = new ArrayList<>();
        testMethods = new ArrayList<>();
        afterMethods = new ArrayList<>();

        Class<?> alreadyFoundAnnotation;
        for(Method method : allMethods) {
            alreadyFoundAnnotation = null;
            if(method.isAnnotationPresent(Before.class)) {
                beforeMethods.add(method);
                alreadyFoundAnnotation = Before.class;
            }
            if(method.isAnnotationPresent(Test.class)) {
                if(nonNull(alreadyFoundAnnotation)) {
                    throw new WrongAnnotationUsageException(alreadyFoundAnnotation, Test.class, method);
                }
                testMethods.add(method);
                alreadyFoundAnnotation = Test.class;
                testMethodsNumber++;
            }
            if(method.isAnnotationPresent(After.class)) {
                if(nonNull(alreadyFoundAnnotation)) {
                    throw new WrongAnnotationUsageException(alreadyFoundAnnotation, After.class, method);
                }
                afterMethods.add(method);
            }
        }

        try {
            this.noArgsConstructor = testScenariosHolder.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("Test class has no zero arguments constructor");
        }
    }

    private void executeTests() {
        failedTestResultList = new ArrayList<>();
        single_test_loop:for(Method testMethod : testMethods) {
            Object testScenariosHolderEntity = constructTestEntity();
            for(Method beforeMethod : beforeMethods) {
                try {
                    beforeMethod.invoke(testScenariosHolderEntity);
                }catch (Exception e) {
                    failedTestResultList.add(ExceptionTestResult.beforeException(e.getCause(), testMethod));
                    continue single_test_loop;
                }
            }
            try {
                testMethod.invoke(testScenariosHolderEntity);
            }catch (Exception e) {
                failedTestResultList.add(ExceptionTestResult.testException(e.getCause(), testMethod));
                continue;
            }
            for(Method afterMethod : afterMethods) {
                try {
                    afterMethod.invoke(testScenariosHolderEntity);
                }catch (Exception e) {
                    failedTestResultList.add(ExceptionTestResult.afterException(e.getCause(), testMethod));
                    continue single_test_loop;
                }
            }
        }
    }

    private void showResult() {
        System.out.println("\n" + testMethodsNumber + " test(-s) was executed");
        if(failedTestResultList.isEmpty()) {
            System.out.println("All tests was completed successfully");
        }else{
            System.out.println(String.format(
                    "%d of %d test(-s) was executed with exception",
                    failedTestResultList.size(),
                    testMethodsNumber
            ));
            for(ExceptionTestResult failedTestResult : failedTestResultList) {
                System.out.println(failedTestResult.toString());
            }
        }
    }

    private Object constructTestEntity() {
        try {
            return noArgsConstructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new EntityCreationException(testScenariosHolder, e);
        }
    }


    class InitStep {
        MethodsPreparedStep prepareMethods() {
            TestExecutor.this.prepareMethods();
            return new MethodsPreparedStep();
        }
    }

    class MethodsPreparedStep {
        FinalStep executeTests() {
            TestExecutor.this.executeTests();
            return new FinalStep();
        }
    }

    class FinalStep {
        void showResult() {
            TestExecutor.this.showResult();
        }
    }
}
