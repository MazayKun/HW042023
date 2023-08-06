package ru.mikheev.kirill.hw16patterns.facade.email;

public class EmailSender {

    public boolean send(String emailAddress, String text) {
        System.out.println("Email with text " + text  + " successfully sent to mailbox " + emailAddress);
        return true;
    }
}
