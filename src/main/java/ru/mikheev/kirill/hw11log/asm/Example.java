package ru.mikheev.kirill.hw11log.asm;

import ru.mikheev.kirill.hw11log.asm.scanner.Log;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Example {

    @Log
    public void logCurrTime() {
        System.out.println(Instant.now());
    }

    @Log
    public int sum(int a, int b) {
        return a + b;
    }

    @Log
    public LocalDateTime convertInstant(Instant time) {
        return LocalDateTime.ofInstant(time, ZoneId.of("Europe/Moscow"));
    }
}
