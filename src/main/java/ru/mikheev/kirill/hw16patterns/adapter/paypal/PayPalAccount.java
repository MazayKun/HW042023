package ru.mikheev.kirill.hw16patterns.adapter.paypal;

import java.math.BigDecimal;

public class PayPalAccount {

    private String pass;

    public PayPalAccount(String pass) {
        this.pass = pass;
    }

    public void getMoney(String pass, BigDecimal amount) {
        if( ! this.pass.equals(pass)) throw new RuntimeException("Wrong password");
        System.out.println("Successfully withdrawn " + amount + " from pay pal account");
    }
}
