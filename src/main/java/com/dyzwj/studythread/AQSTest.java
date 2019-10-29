package com.dyzwj.studythread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AQSTest {


    private static ReentrantLock lock = new ReentrantLock();

    private static Condition condition = lock.newCondition();




    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                lock.lock();
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            });
            thread.start();
        }
    }
}
