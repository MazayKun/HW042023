package ru.mikheev.kirill.hw13bank.util.exception.internal;

public class NotEnoughBanknotesException extends InternalException {
    public NotEnoughBanknotesException(String denomination, int requiredAmount, int totalAmount) {
        super("Not enough " + denomination + " banknotes. Required - " + requiredAmount + ", total amount - " + totalAmount);
    }
}
