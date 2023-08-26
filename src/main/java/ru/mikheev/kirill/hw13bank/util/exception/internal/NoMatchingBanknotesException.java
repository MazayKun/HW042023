package ru.mikheev.kirill.hw13bank.util.exception.internal;

import ru.mikheev.kirill.hw13bank.logic.container.ATMContainer;

public class NoMatchingBanknotesException extends InternalException {

    public NoMatchingBanknotesException(int requiredAmount, ATMContainer atmContainer) {
        super("No matching banknotes in ATM. Required amount - " + requiredAmount + ",\nATM state - " + atmContainer);
    }

}
