package com.dyzwj.studythread.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ExecutorsTest {


    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);

//        scheduledExecutorService.schedule( () -> System.out.println("定时线程池执行...."),1, TimeUnit.SECONDS);

//        scheduledExecutorService.scheduleWithFixedDelay( () -> System.out.println("定时线程执行..."),5,1,TimeUnit.SECONDS);
           scheduledExecutorService.scheduleAtFixedRate( () -> System.out.println("定时线程执行..."),5,1,TimeUnit.SECONDS);



    }




}
