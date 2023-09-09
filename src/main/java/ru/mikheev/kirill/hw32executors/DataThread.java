package ru.mikheev.kirill.hw32executors;

public class DataThread extends Thread {

    private final int threadNumber;
    private final DoubleThreadWriter doubleThreadWriter;

    private boolean reversed = false;
    private int counter = 1;

    public DataThread(int threadNumber, DoubleThreadWriter doubleThreadWriter) {
        this.threadNumber = threadNumber;
        this.doubleThreadWriter = doubleThreadWriter;
    }

    @Override
    public void run() {
        while(true) {
            try {
                doubleThreadWriter.write(threadNumber, counter);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(reversed) {
                counter--;
            }else{
                counter++;
            }
            if(counter == 10 || counter == 1) {
                reversed = !reversed;
            }
        }
    }
}
