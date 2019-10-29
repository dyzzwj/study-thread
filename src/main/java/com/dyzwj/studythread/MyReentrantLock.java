package com.dyzwj.studythread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;


/**
 * 通过synchronize自定义锁
 */
public class MyReentrantLock implements Lock {

    //锁的标志
    private boolean isLock = false;

    //实现可重入 0：可获取锁  >1：锁重入
    private int count = 0;


    //保存获取锁的线程
    private Thread thread;


    @Override
    public synchronized void lock() {

        Thread currentThread = Thread.currentThread();

        while (isLock && currentThread != thread ){

            try{
                wait();
            }catch (InterruptedException e){

            }
        }
        isLock = true;
        thread = Thread.currentThread();
        count ++;

    }


    @Override
    public synchronized void unlock() {
        Thread currentThread = Thread.currentThread();
        if(currentThread == thread){
            count --;
            if(count == 0){
                thread = null;
                isLock = false;
            }
        }


    }



    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }



    @Override
    public Condition newCondition() {
        return null;
    }
}


class Test3{

    private MyReentrantLock lock = new MyReentrantLock();


    private int count;

    public void getNext(){
        lock.lock();
        count++;
        lock.unlock();
        System.out.println(count);
    }

    public static void main(String[] args) {

        Test3 res = new Test3();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    res.getNext();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    res.getNext();
                }            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    res.getNext();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    res.getNext();
                }
            }
        }).start();

    }


}



class Test4{


    private MyReentrantLock lock = new MyReentrantLock();

    public void a(){
        lock.lock();
        System.out.println("a....");
        b();
        lock.unlock();
    }
    public void b(){
        lock.lock();
        System.out.println("b....");
        lock.unlock();
    }


    public static void main(String[] args) {

        Test4 test4 = new Test4();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test4.a();
            }
        }).start();
    }


}



