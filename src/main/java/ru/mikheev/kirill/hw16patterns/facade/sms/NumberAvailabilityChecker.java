package ru.mikheev.kirill.hw16patterns.facade.sms;

public class NumberAvailabilityChecker {

    private final String phoneNumber;

    public NumberAvailabilityChecker(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean ping() {
        System.out.println("Phone number is available");
        return true;
    }
}
