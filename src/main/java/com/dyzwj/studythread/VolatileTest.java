package com.dyzwj.studythread;

public class VolatileTest {

    private static boolean flag = false;
//    private static volatile boolean flag = false;


    public static void main(String[] args) {
//       test1();
        test2();
    }


    public static void test2(){
        Demo d = new Demo();

        Thread t1 = new Thread(){
            @Override
            public void run() {
                super.run();
//                try{
//                   Thread.sleep(500);
//
//                }catch(InterruptedException e){
//
//                }

                d.read();

            }
        };


        Thread t2 = new Thread(){

            @Override
            public void run() {
                super.run();

                d.write();

            }
        };

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        }catch(InterruptedException e){

        }


        d.print();


    }

    public static void test1(){
        Thread t1 = new Thread(){
            @Override
            public void run() {
                super.run();
                while(!flag){

                }
            }
        };
        t1.start();
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){

        }
        flag = true;
    }


    static class Demo {

        boolean flag = false;
        int a = 0;

        public void write(){
            this.a = 1;
            flag = true;

        }


        public void read(){
            if(flag){
                this.a++;
            }
        }

        public void print(){
            System.out.println(this.a);
        }

    }





}
