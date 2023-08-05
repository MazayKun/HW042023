package ru.mikheev.kirill.hw13bank.exception;

import ru.mikheev.kirill.hw13bank.container.ATMContainer;

public class NoMatchingBanknotesException extends RuntimeException {

    public NoMatchingBanknotesException(int requiredAmount, ATMContainer atmContainer) {
        super("No matching banknotes in ATM. Required amount - " + requiredAmount + ",\nATM state - " + atmContainer);
    }

}
