package ru.mikheev.kirill.hw16patterns.adapter.creditcard;

import java.math.BigDecimal;
import java.util.Arrays;

public class CreditCardAccount {

    private char[] pin;

    public CreditCardAccount(char[] pin) {
        if(pin.length != 4) throw new RuntimeException("Pin code must be 4 characters length");
        this.pin = pin;
    }

    public void getMoney(char[] pin, BigDecimal amount) {
        if(Arrays.compare(this.pin, pin) != 0) throw new RuntimeException("Wrong pin code");
        System.out.println("Successfully withdrawn " + amount + " from credit card");
    }
}
