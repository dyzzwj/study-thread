package com.dyzwj.studythread;

import org.springframework.aop.framework.autoproxy.target.AbstractBeanFactoryBasedTargetSourceCreator;

import java.util.concurrent.locks.Lock;

public class FinalTest {


    private int a;
    private final int b;
    private static FinalTest finalTest;

    public FinalTest(){
        this.a = 1;
        this.b = 2;
    }


    public static void writer(){
        finalTest = new FinalTest();
    }


    public static void reader() {
        FinalTest demo = finalTest; // 3.读对象引用
        int a = demo.a;    //4.读普通域
        int b = demo.b;    //5.读final域
        System.out.println(a + ":" + b);
    }


    public static void main(String[] args) {

        FinalTest test = new FinalTest();

        Thread t1 = new Thread(){
            @Override
            public void run() {
                super.run();
                test.writer();
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){

                }
                super.run();
                test.reader();
            }


        };


        t1.start();
        t2.start();



    }


}



class Father{

    public final void walk(){}
    public final void walk(String name){}

}

class Son extends Father{


}