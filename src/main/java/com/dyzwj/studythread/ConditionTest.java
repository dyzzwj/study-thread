package com.dyzwj.studythread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {


    private static ReentrantLock lock = new ReentrantLock();
    //消费
    private Condition notEmpty = lock.newCondition();
    //生产
    private Condition notFull = lock.newCondition();


    int i = 1;

    private BlockingQueue<String> queue = new ArrayBlockingQueue<>(100);


    public static void main(String[] args) {


        ConditionTest test  = new ConditionTest();

        new Thread(){
            @Override
            public void run() {
                super.run();
                while (true) {
                    test.produce();
                    try{
                        Thread.sleep(100);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                }
            }
        }.start();


        new Thread(){
            @Override
            public void run() {
                super.run();
                while(true) {
                    test.consume();
                    try{
                        Thread.sleep(100);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    //生产
    public void produce(){

        lock.lock();
        try{

            System.out.println("生产者生产商品"+i);
            queue.add("商品"+i++);
        }finally {
            lock.unlock();
        }


    }

    //消费
    public void consume(){

        lock.lock();
        try{

            String str = queue.take();
            System.out.println("消费者消费商品"+str);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }


    }





}
