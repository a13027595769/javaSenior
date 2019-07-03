package com;


import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("**********come in Callable");
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        return 1024;
    }
}

public class CallableDemo {

    public static void main(String[] args) throws Exception {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        Thread t1 = new Thread(futureTask,"AA");
        t1.start();
        int result01 = 100;
        System.out.println(Thread.currentThread().getName()+"********");
        int result02 = futureTask.get();
        while (!futureTask.isDone()){

        }

        System.out.println("********result:"+(result01+result02));
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
