package com.dyzwj.studythread;

import java.util.Date;

public class InterruptTest {


    public static void main(String[] args)throws InterruptedException{
          test1();
//            test2();
//            test3();


    }


    /**
     * 在运行时代码没有调用可以抛出InterruptedException的方法
     */
    public static void test3() throws InterruptedException{

        Thread t1 = new Thread(){
            @Override
            public void run() {
                super.run();
                int i= 0;
                while(true){
                    if(!Thread.interrupted()){
                        i++;
                        System.out.println("自定义线程执行了"+i+"次");
                    }else{
                        System.out.println("自定义线程被中断");
                        return ;
                    }

                }
            }
        };

        t1.start();
        Thread.sleep(2000);
        System.out.println("主线程:休眠2毫秒后发送中断信号...");
        t1.interrupt();

    }



    /**
     * 在运行时代码这调用了抛出InterruptedException的方法
     */

    public static void test2() throws InterruptedException{
        Thread t1 = new Thread(){
            @Override
            public void run() {
                super.run();
                for(int i = 0; i < 10 ;i++){
                    try{
                        Thread.sleep(4000);
                        System.out.println("自定义线程当前时间："+new Date().toLocaleString());
                    }catch(InterruptedException e){
                        e.printStackTrace();
                        System.out.println("自定义线程:收到中断信号，总共循环了"+i+"次...");
                        return ;
                    }

                }
            }
        };

        t1.start();
        Thread.sleep(9000);
        System.out.println("主线程：等待9秒后发送中断信号...");
        t1.interrupt();

    }




    public static void test1(){
        Thread t1 = new Thread(){
            @Override
            public void run() {
//                super.run();
//
                try{
                    Thread.sleep(5000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }

                System.out.println("interrupted" + Thread.interrupted());
                System.out.println("t1"+Thread.currentThread().isInterrupted());



            }
        };
        t1.start();

        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){

        }
        t1.interrupt();

        System.out.println(t1.isInterrupted());
//        System.out.println("over");
    }
















}
