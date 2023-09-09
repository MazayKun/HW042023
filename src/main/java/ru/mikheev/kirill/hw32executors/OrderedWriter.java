package ru.mikheev.kirill.hw32executors;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OrderedWriter {

    private final Lock lock = new ReentrantLock();
    private final Condition writeReleaseCondition = lock.newCondition();
    private final Queue<Integer> accessQueue = new LinkedList<>();

    public OrderedWriter(int threadNumber) {
        for(int i = 1; i <= threadNumber; i++) {
            accessQueue.add(i);
        }
    }

    public void write(int accessNumber, String stringToWrite) throws InterruptedException {
        lock.lock();
        while(accessNumber != accessQueue.peek()) writeReleaseCondition.await();
        System.out.println("Thread " + accessNumber + " : " + stringToWrite);
        accessQueue.add(accessQueue.remove());
        writeReleaseCondition.signalAll();
        lock.unlock();
    }
}
