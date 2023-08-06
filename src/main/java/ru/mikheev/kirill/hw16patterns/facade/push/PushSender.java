package ru.mikheev.kirill.hw16patterns.facade.push;

public class PushSender {

    public boolean send(long deviceId, String text) {
        System.out.println("Push with text " + text  + " successfully shown on device with id " + deviceId);
        return true;
    }
}
