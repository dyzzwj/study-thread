package com.dyzwj.studythread;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class CallableTest {


    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> submit = executorService.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                return "实现Callable接口";
            }
        });


        String result = submit.get();
        System.out.println(result);


    }




}
