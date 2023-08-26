package ru.mikheev.kirill.hw16patterns.facade.sms;

public class SmsSender {

    public boolean send(String phoneNumber, String text) {
        System.out.println("Sms with text " + text  + " successfully sent to number " + phoneNumber);
        return true;
    }
}
