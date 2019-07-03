package com.mypack.service;

import java.util.concurrent.TimeUnit;
class myDate{
    volatile int number = 0;
    public void addTo60(){
        this.number=60;
    }
}
public class Demo1 {
    public static void main(String[] args) {
        myDate myDate = new myDate();
        new Thread(() -> {
            System.out.println("coming");
            try {

                TimeUnit.SECONDS.sleep(3);
            }catch (Exception e){

            }
            myDate.addTo60();
            System.out.println("ending");
        }).start();

        while(myDate.number==0){

        }

        System.out.println("-------------");
    }

}
