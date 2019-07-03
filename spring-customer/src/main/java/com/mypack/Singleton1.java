package com.mypack;

public class Singleton1 {

    private static volatile Singleton1 instance = null;
    private Singleton1(){
        System.out.println("---------------");
    }
    public static Singleton1 getInstance(){
        if(instance==null){


        synchronized (Singleton1.class){
            if(instance==null){
                instance = new Singleton1();
            }
        }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i <10 ; i++) {
            new Thread(() -> {
                    Singleton1.getInstance();
            },String.valueOf(i)).start();
        }
    }

}
