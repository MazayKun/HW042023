package ru.mikheev.kirill.hw32executors;

public class DataThread extends Thread {

    private final int accessNumber;
    private final OrderedWriter orderedWriter;

    private boolean reversed = false;
    private int counter = 1;

    public DataThread(int accessNumber, OrderedWriter orderedWriter) {
        this.accessNumber = accessNumber;
        this.orderedWriter = orderedWriter;
    }

    @Override
    public void run() {
        while(true) {
            try {
                orderedWriter.write(accessNumber, counter);
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
