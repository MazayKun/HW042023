package ru.mikheev.kirill.hw13bank.exception.internal;

import ru.mikheev.kirill.hw13bank.dao.ATMContainer;

public class NoMatchingBanknotesException extends InternalException {

    public NoMatchingBanknotesException(int requiredAmount, ATMContainer atmContainer) {
        super("No matching banknotes in ATM. Required amount - " + requiredAmount + ",\nATM state - " + atmContainer);
    }

}
