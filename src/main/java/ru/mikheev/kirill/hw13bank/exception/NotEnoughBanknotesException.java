package ru.mikheev.kirill.hw13bank.exception;

public class NotEnoughBanknotesException extends RuntimeException {
    public NotEnoughBanknotesException(String denomination, int requiredAmount, int totalAmount) {
        super("Not enough " + denomination + " banknotes. Required - " + requiredAmount + ", total amount - " + totalAmount);
    }
}
