package ru.mikheev.kirill.hw16patterns.adapter;

import ru.mikheev.kirill.hw16patterns.adapter.account.BankAccount;
import ru.mikheev.kirill.hw16patterns.adapter.account.method.CreditCardMethod;
import ru.mikheev.kirill.hw16patterns.adapter.account.method.PayPalMethod;
import ru.mikheev.kirill.hw16patterns.adapter.creditcard.CreditCardAccount;
import ru.mikheev.kirill.hw16patterns.adapter.paypal.PayPalAccount;

import java.math.BigDecimal;

public class AdapterTest {
    public static void main(String[] args) {
        BankAccount myBankAccount = new BankAccount(4000);
        CreditCardAccount fathersCreditCardAccount = new CreditCardAccount(new char[]{'0', '0', '0', '0'});
        PayPalAccount friendsPayPalAccount = new PayPalAccount("hardToBreakPassword");
        CreditCardMethod myCreditCardReplenisher = new CreditCardMethod(myBankAccount);
        PayPalMethod myPayPalReplenisher = new PayPalMethod(myBankAccount);
        myCreditCardReplenisher.transferMoney(fathersCreditCardAccount, new char[]{'0', '0', '0', '0'}, new BigDecimal(1000));
        myPayPalReplenisher.transferMoney(friendsPayPalAccount, "hardToBreakPassword", new BigDecimal(1000));

    }
}
