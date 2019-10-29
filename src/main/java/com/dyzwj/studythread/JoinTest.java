package com.dyzwj.studythread;


/**
 * 执行join()方法的线程会等待join()方法的调用线程终止后才会继续执行
 *
 * 如果一个线程实例A执行了threadB.join(),其含义是：当前线程A会等待threadB线程终止后threadA才会继续执行
 */
public class JoinTest {


    public static void main(String[] args) throws InterruptedException{

//      test1();
        test2();

    }


    public static void test2(){

        Thread previousThread = Thread.currentThread();
        for (int i = 0; i < 10; i++){
            Thread curThread = new JoinThread(previousThread);
            curThread.start();
            previousThread = curThread;
        }



    }





    public static void test1() throws InterruptedException{
        Thread t1 = new Thread(){
            @Override
            public void run() {
                super.run();
                try{
                    Thread.sleep(10000);
                    System.out.println("t1执行完了");
                }catch(InterruptedException e){

                }
            }
        };

        t1.start();
        t1.join();
        System.out.println("主线程执行完了");
    }

    static class JoinThread extends Thread{

        private Thread thread;
        public JoinThread(Thread thread){
            this.thread = thread;
        }


        @Override
        public void run() {
            super.run();
            try{
                thread.join();
                System.out.println(thread.getName() + " terminated..");
            }catch (InterruptedException e){

            }





        }
    }



}



















