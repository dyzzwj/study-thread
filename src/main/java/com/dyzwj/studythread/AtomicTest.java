package com.dyzwj.studythread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * concurrent包下的原子类，每个基本数据类型都有与之对应的原子类，采用CAS无锁算法
 */


public class AtomicTest {

    private static int num = 0;
    private static AtomicInteger number = new AtomicInteger(0);
    private static CountDownLatch latch = new CountDownLatch(10);


    private static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException{


        for(int i = 0;i<10;i++){

            new Thread(){
                @Override
                public void run() {
                    super.run();

//                    lock.lock();
                    try{
                        for (int j = 0; j < 10000; j++) {
//                            num++;
                            number.getAndIncrement();
                        }
                        latch.countDown();
                    }finally {
//                        lock.unlock();
                    }


                }
            }.start();



        }

        latch.await();
//        System.out.println(num);
        System.out.println(number);
    }

}





