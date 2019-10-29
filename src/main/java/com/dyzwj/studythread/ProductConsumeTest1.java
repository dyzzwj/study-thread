package com.dyzwj.studythread;


public class ProductConsumeTest1 {


   public static Resource resource = new Resource();


    public static void main(String[] args) {

        new Thread(new Product(resource)).start();
        new Thread(new Product(resource)).start();
//        new Thread(new Product(resource)).start();
//        new Thread(new Product(resource)).start();




        new Thread(new Consumer(resource)).start();
        new Thread(new Consumer(resource)).start();
//        new Thread(new Consumer(resource)).start();
//        new Thread(new Consumer(resource)).start();


    }

}

class Product implements Runnable{

    Resource resource;

    public Product(Resource resource){
        this.resource = resource;
    }

    @Override
    public void run() {

        for(int i = 0;i<10;i++){
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){


            }

            synchronized (resource){
                System.out.println("生产者线程"+Thread.currentThread().getName()+"拿到锁");
                while(resource.get() == 10){
                    try{
//                        System.out.println("生产者等待");
                        resource.wait();

                    }catch (InterruptedException e){

                    }
                }

//                System.out.println(Thread.currentThread().getName()+"的锁："+resource);
                resource.inc();
                System.out.println(Thread.currentThread().getName() + "生产者生产，目前总共有" + resource.get());
                resource.notifyAll();

                System.out.println("生产者线程"+Thread.currentThread().getName()+"释放锁");

            }
        }





    }




}


class Consumer implements Runnable{


    Resource resource;

    public Consumer(Resource resource){
        this.resource = resource;
    }


    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }

            synchronized (resource) {
                System.out.println("消费者线程" + Thread.currentThread().getName() + "拿到锁");
                while (resource.get() == 0) {
                    try {
//                    System.out.println("消费者等待...........");
                        resource.wait();
                    } catch (InterruptedException e) {

                    }

                }

//            System.out.println(Thread.currentThread().getName()+"的锁："+resource);
                resource.dec();
                System.out.println(Thread.currentThread().getName() + "消费者消费，目前总共有" + resource.get());

                resource.notifyAll();
                System.out.println("消费者线程" + Thread.currentThread().getName() + "释放锁");

            }
        }
    }
}




class Resource {

    private int count;

    public void inc(){
        count++;
    }

    public void dec(){
        count--;
    }

    public int get(){
        return count;
    }

}




