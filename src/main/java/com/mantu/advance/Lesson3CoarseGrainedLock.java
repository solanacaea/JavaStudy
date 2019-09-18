package com.mantu.advance;

import java.util.concurrent.locks.ReentrantLock;


/**
 * blog http://www.cnblogs.com/mantu/
 * github https://github.com/mantuliu/
 * @author mantu
 *
 */
public class Lesson3CoarseGrainedLock implements Runnable{
    public static ReentrantLock lock = new ReentrantLock();
    public static void main(String args[]){
        for(int i=0;i<100;i++){
            Thread thread =new Thread(new Lesson3CoarseGrainedLock());
            thread.start();
        }
    }

    @Override
    public void run() {
        try {
            lock.lock();
            Thread.sleep(1000);
        } 
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally{
            lock.unlock();
            System.out.println("线程"+Thread.currentThread().getId()+"执行完毕");
        }
    }
}
