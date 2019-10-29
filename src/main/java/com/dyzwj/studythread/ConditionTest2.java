package com.dyzwj.studythread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest2 {


    private ReentrantLock lock = new ReentrantLock();


    private Condition producer = lock.newCondition();

    private Condition consumer  = lock.newCondition();


    private int count;

    private int max = 10;

    private int min = 0;

    public static void main(String[] args) {

        ConditionTest2 test2 = new ConditionTest2();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    test2.produce();
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){

                    }

                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    test2.produce();
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){

                    }

                }

            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    test2.produce();
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){

                    }

                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    test2.consume();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    test2.consume();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                }
            }
        }).start();
    }




    public void produce(){



            lock.lock();
            try {
                while (count >= max) {
                    producer.await();
                }
                count++;
                System.out.println(Thread.currentThread().getName()+"生产者生产了第" + count + "个产品");
                consumer.signal();

            } catch (InterruptedException e) {

            } finally {
                lock.unlock();
            }

    }


    public void consume() {
        lock.lock();
            try {
                while (count <= min) {
                    consumer.await();
                }
                count--;
                System.out.println(Thread.currentThread().getName()+"消费者消费了第" + count + "个产品");
                producer.signal();
            } catch (InterruptedException e) {

            } finally {
                lock.unlock();
            }

    }


}
