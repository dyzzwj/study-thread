package com.dyzwj.studythread;


/**
 * JVM会在所有执行的线程都是守护线程的时候退出。
 */
public class DaemonThreadTest {


    public static void main(String[] args) {

        Thread t1 = new Thread(){
            @Override
            public void run() {
                super.run();
                try{
                    Thread.sleep(10000);
                    System.out.println("t1线程执行完毕");
                }catch(InterruptedException e){

                }

            }
        };
        t1.setDaemon(true);
        t1.start();

        System.out.println("主线程执行完毕");

    }
}
