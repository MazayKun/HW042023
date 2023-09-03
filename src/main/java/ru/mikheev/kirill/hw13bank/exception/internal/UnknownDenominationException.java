package ru.mikheev.kirill.hw13bank.exception.internal;

import java.util.Map;

public class UnknownDenominationException extends InternalException {

    public UnknownDenominationException(int denomination) {
        super("Unknown denomination " + denomination);
    }

    public UnknownDenominationException(Map<Integer, Integer> moneyBundle) {
        super("Unknown denomination(-s) found in bundle " + moneyBundle.toString());
    }
}
