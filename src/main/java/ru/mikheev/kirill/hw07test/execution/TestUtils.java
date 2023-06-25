package ru.mikheev.kirill.hw07test.execution;

public class TestUtils {

    public static void performTestScenarios(Class<?>... testScenariosHolders) {
        TestExecutor testExecutor = new TestExecutor();
        for(var testScenariosHolder : testScenariosHolders) {
            testExecutor
                    .initTestScenariosHolder(testScenariosHolder)
                    .prepareMethods()
                    .executeTests()
                    .showResult();
        }
    }
}
