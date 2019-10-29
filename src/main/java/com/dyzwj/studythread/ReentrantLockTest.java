package com.dyzwj.studythread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {




    public static void main(String[] args) throws InterruptedException {

        Test1 t1 = new Test1();
        Test1 t2 = new Test1();

        Thread t3 = new Thread(t1);

        Thread t4 = new Thread(t2);
        t3.start();
        t4.start();
        t3.join();
        t4.join();

        System.out.println(Test1.num);
    }


}


class Test1 implements Runnable{


    private static ReentrantLock lock = new ReentrantLock();
    public static int num = 0;

    //可重入
    @Override
    public void run() {

        for(int j = 0; j < 1000000; j++){
            lock.lock();
            lock.lock();
            try{
                num ++;
            }finally {
                lock.unlock();
                lock.unlock();
            }

        }

    }
}