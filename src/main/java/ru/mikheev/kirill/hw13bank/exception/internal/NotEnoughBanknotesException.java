package ru.mikheev.kirill.hw13bank.exception.internal;

public class NotEnoughBanknotesException extends InternalException {
    public NotEnoughBanknotesException(int denomination, int requiredAmount, int totalAmount) {
        super("Not enough " + denomination + " banknotes. Required - " + requiredAmount + ", total amount - " + totalAmount);
    }
}
