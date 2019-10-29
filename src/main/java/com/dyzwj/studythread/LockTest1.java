package com.dyzwj.studythread;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;

/**
 * 需求现在有100张火车票，有两个窗口同时抢火车票，请使用多线程模拟抢票效果。
 */


public class LockTest1 {

    static int tick = 100;

    public synchronized static void sale(){

        if(tick>0) {
            System.out.println(Thread.currentThread().getName()+"---出售第" + --tick + "张票");
        }

    }


    public static void main(String[] args) {

        Thread t1 = new Thread(){
            @Override
            public void run() {
                super.run();
                while(tick > 0 ){
                    try{
                        sale();
                        Thread.sleep(100);
                    }catch (InterruptedException e){

                    }

                }

            }
        };


        Thread t2 = new Thread(){
            @Override
            public void run() {
                super.run();
                while(tick > 0 ){
                    try{
                        sale();
                        Thread.sleep(100);
                    }catch (InterruptedException e){

                    }
                }
            }
        };
        t1.start();
        t2.start();


    }








}
