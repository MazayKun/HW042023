package ru.mikheev.kirill.hw32executors;

public class DataThread extends Thread {

    private final int accessNumber;
    private final OrderedWriter orderedWriter;

    private boolean reversed = false;

    public DataThread(int accessNumber, OrderedWriter orderedWriter) {
        this.accessNumber = accessNumber;
        this.orderedWriter = orderedWriter;
    }

    @Override
    public void run() {
        StringBuilder resultBuilder = new StringBuilder();
        while(true) {
            resultBuilder.setLength(0);
            if(reversed) {
                for(int i = 10; i >= 1; i--) {
                    resultBuilder.append(i).append(' ');
                }
            }else{
                for(int i = 1; i <= 10; i++) {
                    resultBuilder.append(i).append(' ');
                }
            }
            reversed = !reversed;
            try {
                orderedWriter.write(accessNumber, resultBuilder.toString());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
