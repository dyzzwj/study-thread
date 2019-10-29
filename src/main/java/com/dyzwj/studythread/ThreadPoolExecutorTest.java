package com.dyzwj.studythread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {


    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5,10,
                200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(5));



        for(int i = 0; i < 15 ;i++){

            MyTask task = new MyTask(i);
            poolExecutor.execute(task);
            System.out.println("线程池中线程数目："+ poolExecutor.getPoolSize()
            +"，队列中等待执行的任务数目："+poolExecutor.getQueue().size() +
                    "，线程池中已完成的任务数目：" +poolExecutor.getCompletedTaskCount());

        }
    }





    static class MyTask implements Runnable{

        private int taskNum;


        public MyTask(int taskNum){
            this.taskNum = taskNum;
        }

        @Override
        public void run() {
            System.out.println("正在执行task "+taskNum);
            try {
                Thread.currentThread().sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task "+taskNum+"执行完毕");


        }
    }



}
