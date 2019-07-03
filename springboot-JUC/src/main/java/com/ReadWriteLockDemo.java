package com;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyChahe{//资源类
    private volatile Map<String,Object> map = new HashMap<>();
    private ReentrantReadWriteLock rwlock =  new ReentrantReadWriteLock();
    public void put(String key,Object value){
        rwlock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t正在写入："+key);
            try{
                TimeUnit.MILLISECONDS.sleep(300);
            }catch (Exception e){
                e.printStackTrace();
            }
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t写入完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rwlock.writeLock().unlock();
        }


    }
    public void get(String key){
        
         rwlock.readLock().lock();
         try{
             System.out.println(Thread.currentThread().getName()+"\t正在读取：");
             try{
                 TimeUnit.MILLISECONDS.sleep(300);
             }catch (Exception e){
                 e.printStackTrace();
             }
             Object result = map.get(key);
             System.out.println(Thread.currentThread().getName()+"\t读取完成"+result);
         }catch (Exception e){
             e.printStackTrace();
         }finally {
             rwlock.readLock().unlock();
         }

    }

}

public class ReadWriteLockDemo {


    public static void main(String[] args) {
        MyChahe myChahe = new MyChahe();
        for (int i = 0; i < 5; i++) {
                final int tempInt = i;
            new Thread(() -> {
                myChahe.put(tempInt+"",tempInt+"");
            },String.valueOf(i)).start();

        }

        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myChahe.get(tempInt+"");
            },String.valueOf(i)).start();

        }

       
    }
}
