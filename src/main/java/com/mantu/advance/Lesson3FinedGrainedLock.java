package com.mantu.advance;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;


/**
 * blog http://www.cnblogs.com/mantu/
 * github https://github.com/mantuliu/
 * @author mantu
 *
 */
public class Lesson3FinedGrainedLock implements Runnable{
    public static HashMap<Integer,ReentrantLock> lockMap = new HashMap<Integer,ReentrantLock>();
    public static void main(String args[]){
        init();
        for(int i=0;i<100;i++){
            Thread thread =new Thread(new Lesson3FinedGrainedLock());
            thread.start();
        }
    }
    public static void init(){
        for(int i=0;i<10;i++){
            lockMap.put(new Integer(i),new ReentrantLock());
        }
    }
    @Override
    public void run() {
        ReentrantLock lock = Lesson3FinedGrainedLock.lockMap.get((int)(Thread.currentThread().getId())%10);
        try {
            lock.lock();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally{
            lock.unlock();
            System.out.println("线程"+Thread.currentThread().getId()+"执行完毕");
        }       
    }
}
