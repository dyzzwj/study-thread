package com.dyzwj.studythread;

public class WaitTest {

    public static void main(String[] args) throws InterruptedException{
        Object obj = new Object();
        Object lock = new Object();

        synchronized (obj){
            obj.wait();
        }
    }
}
