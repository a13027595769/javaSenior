package com.example.springbootmianshi2;

public class SpringbootMianshi2Application {
    public static void main(String[] args) {
        System.out.println("hello******GC");
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
