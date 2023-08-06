package ru.mikheev.kirill.hw16patterns.adapter.account.method;

import ru.mikheev.kirill.hw16patterns.adapter.account.BankAccount;
import ru.mikheev.kirill.hw16patterns.adapter.creditcard.CreditCardAccount;

import java.math.BigDecimal;

public class CreditCardMethod {

    private final BankAccount bankAccount;

    public CreditCardMethod(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public boolean transferMoney(CreditCardAccount creditCardAccount, char[] pin, BigDecimal amount) {
        try {
            creditCardAccount.getMoney(pin, amount);
        }catch (RuntimeException e) {
            return false;
        }
        bankAccount.replenishAccount(amount);
        return true;
    }
}
