package com;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource{
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void loopA(int totalLoop){
         lock.lock();
         try{
                if(number!=1){
                    condition1.await();
                }
             for (int i = 1; i <= 5; i++) {
                 System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+totalLoop);
             }
             number = 2;
             condition2.signal();
         }catch (Exception e){
             e.printStackTrace();
         }finally {
             lock.unlock();
         }
    }

    public void loopB(int totalLoop){
        lock.lock();
        try{
            if(number!=2){
                condition2.await();
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+totalLoop);
            }
            number = 3;
            condition3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void loopC(int totalLoop){
        lock.lock();
        try{
            if(number!=3){
                condition3.await();
            }
            for (int i = 1; i <= 20; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+totalLoop);
            }
            number = 1;
            condition1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}


public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                shareResource.loopA(i);
            }
        },"a").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                shareResource.loopB(i);
            }
        },"b").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                shareResource.loopC(i);
            }
        },"c").start();
    }
}
