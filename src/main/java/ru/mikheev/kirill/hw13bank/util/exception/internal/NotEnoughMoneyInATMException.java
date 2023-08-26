package ru.mikheev.kirill.hw13bank.util.exception.internal;

public class NotEnoughMoneyInATMException extends InternalException {

    public NotEnoughMoneyInATMException(int requiredAmount, int totalAmount) {
        super("There is not enough money in ATM. Required - " + requiredAmount + ", total amount - " + totalAmount);
    }
}
