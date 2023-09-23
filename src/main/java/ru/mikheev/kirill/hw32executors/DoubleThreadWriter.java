package ru.mikheev.kirill.hw32executors;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DoubleThreadWriter {

    private final Lock lock = new ReentrantLock();
    private final Condition writeReleaseCondition = lock.newCondition();
    private int currThread = 1;


    public void write(int threadNumber, int intToWrite) throws InterruptedException {
        lock.lock();
        while(currThread != threadNumber) writeReleaseCondition.await();
        System.out.println("Thread " + threadNumber + " : " + intToWrite);
        if(currThread == 1) {
            currThread = 2;
        }else{
            currThread = 1;
        }
        writeReleaseCondition.signal();
        lock.unlock();
    }
}
