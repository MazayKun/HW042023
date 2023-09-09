package ru.mikheev.kirill.hw32executors;

public class Main {

    public static void main(String[] args) {
        int threadNumber = 10;
        OrderedWriter orderedWriter = new OrderedWriter(threadNumber);
        for(int i = 1; i <= threadNumber; i++) {
            DataThread dataThread = new DataThread(i, orderedWriter);
            dataThread.start();
        }
    }
}
