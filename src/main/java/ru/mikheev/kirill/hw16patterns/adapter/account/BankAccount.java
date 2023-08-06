package ru.mikheev.kirill.hw16patterns.adapter.account;

import java.math.BigDecimal;

public class BankAccount {

    private BigDecimal totalAmount;

    public BankAccount(double initAmount) {
        if(initAmount < 0) throw new RuntimeException("Initial amount can not be less than zero");
        totalAmount = new BigDecimal(initAmount);
    }

    public void replenishAccount(BigDecimal amount) {
        if(amount.signum() <= 0) throw new RuntimeException("Replenish amount can not be less than or equal zero");
        totalAmount = totalAmount.add(amount);
    }
}
