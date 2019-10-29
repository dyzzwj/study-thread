package com.dyzwj.studythread;

public class DeadThreadTest {

    private static String resource_A = "A";
    private static String resource_B = "B";


    public static void main(String[] args) {


            Thread t1 = new Thread(){
                @Override
                public void run() {
                    super.run();
                    synchronized (resource_A){
                        System.out.println("get Resource_A");
                        try{
                            Thread.sleep(3000);
                            synchronized (resource_B){
                                System.out.println("get Resource_B");
                            }
                        }catch(Exception e){
                            e.printStackTrace();

                        }

                    }



                }
            };

            Thread t2 = new Thread(){
                @Override
                public void run() {
                    super.run();
                    synchronized (resource_B){
                        System.out.println("get Resource_B");
                        synchronized (resource_A){
                            System.out.println("get resource_A");
                        }
                    }
                }
            };

            t1.start();
            t2.start();


    }








}
