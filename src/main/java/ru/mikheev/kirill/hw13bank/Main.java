package ru.mikheev.kirill.hw13bank;

import ru.mikheev.kirill.hw13bank.container.MoneyBundle;

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.loadBanknotes(new MoneyBundle(1,1,1,1,1,1));
        System.out.println(atm.getMoney(6000));
        System.out.println(atm.getMoney(5000));
    }
}
