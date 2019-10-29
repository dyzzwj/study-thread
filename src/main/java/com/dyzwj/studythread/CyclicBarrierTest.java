package com.dyzwj.studythread;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {


    public void metting(CyclicBarrier barrier){

        System.out.println(Thread.currentThread().getName() + "到达会议室，等待");
        try {
            barrier.await();
        }catch (Exception e){


        }
        System.out.println(Thread.currentThread().getName() + "等待完毕，开始会议");
    }


    public static void main(String[] args) {
        CyclicBarrierTest test = new CyclicBarrierTest();

        CyclicBarrier barrier = new CyclicBarrier(10, new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "会议开始");
            }
        });

        for(int i = 0; i < 10; i++ ){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    test.metting(barrier);
                }
            }).start();
        }
    }


}
