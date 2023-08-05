package ru.mikheev.kirill.hw13bank.exception;

public class NotEnoughMoneyInATMException extends RuntimeException {

    public NotEnoughMoneyInATMException(int requiredAmount, int totalAmount) {
        super("There is not enough money in ATM. Required - " + requiredAmount + ", total amount - " + totalAmount);
    }
}
