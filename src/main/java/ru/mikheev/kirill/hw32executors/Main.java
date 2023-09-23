package ru.mikheev.kirill.hw32executors;

public class Main {

    public static void main(String[] args) {
        DoubleThreadWriter doubleThreadWriter = new DoubleThreadWriter();
        DataThread dataThread1 = new DataThread(1, doubleThreadWriter);
        DataThread dataThread2 = new DataThread(2, doubleThreadWriter);
        dataThread1.start();
        dataThread2.start();
    }
}
