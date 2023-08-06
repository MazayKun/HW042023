package ru.mikheev.kirill.hw16patterns.adapter.account.method;

import ru.mikheev.kirill.hw16patterns.adapter.account.BankAccount;
import ru.mikheev.kirill.hw16patterns.adapter.paypal.PayPalAccount;

import java.math.BigDecimal;

public class PayPalMethod {

    private final BankAccount bankAccount;

    public PayPalMethod(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public boolean transferMoney(PayPalAccount payPalAccount, String pass, BigDecimal amount) {
        try {
            payPalAccount.getMoney(pass, amount);
        }catch (RuntimeException e) {
            return false;
        }
        bankAccount.replenishAccount(amount);
        return true;
    }
}
