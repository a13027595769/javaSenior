package com;

public class HelloGC {
    public static void main(String[] args) {
        System.out.println("hello******GC");
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
