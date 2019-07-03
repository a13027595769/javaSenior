package com;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <=6; i++) {
            new Thread(() -> {

                System.out.println(Thread.currentThread().getName()+"\t上完自习，离开教室");
                countDownLatch.countDown();
            },CountryEnum.fore_CountryEnum(i).getRetMsg()).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t*******班长走人");


    }

    public static void countDonwLatch()throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i <=6; i++) {
            new Thread(() -> {

                System.out.println(Thread.currentThread().getName()+"\t上完自习，离开教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t*******班长走人");
    }
}
