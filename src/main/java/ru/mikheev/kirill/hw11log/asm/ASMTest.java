package ru.mikheev.kirill.hw11log.asm;


import ru.mikheev.kirill.hw11log.asm.scanner.PackageScanner;

import java.time.Instant;
import java.util.List;

public class ASMTest {
    public static void main(String[] args) {
        PackageScanner.scanForLogAnnotation(ASMTest.class.getPackage());
        Example example = new Example();
        example.logCurrTime();
        System.out.println(example.sum(6, 2));
        System.out.println(example.convertInstant(Instant.now()));
        SecondExample.ifMethod(false, "Hello", List.of("Hello", "World", "!"));
    }
}
