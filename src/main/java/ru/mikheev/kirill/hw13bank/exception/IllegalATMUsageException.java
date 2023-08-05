package ru.mikheev.kirill.hw13bank.exception;

public class IllegalATMUsageException extends RuntimeException {
    public IllegalATMUsageException(String message) {
        super(message);
    }
}
