package ru.mikheev.kirill.hw11log.asm;

import ru.mikheev.kirill.hw11log.asm.scanner.Log;

public class SecondExample {

    @Log
    public static void ifMethod(boolean condition, Object thenBranch, Object elseBranch) {
        if (condition) {
            System.out.println(thenBranch);
        } else {
            System.out.println(elseBranch);
        }
    }
}
