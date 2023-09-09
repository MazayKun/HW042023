package ru.mikheev.kirill.hw13bank.exception.internal;

public class IllegalATMUsageException extends InternalException {
    public IllegalATMUsageException(String message) {
        super(message);
    }
}
