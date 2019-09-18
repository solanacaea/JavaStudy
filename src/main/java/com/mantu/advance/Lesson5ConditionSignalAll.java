package com.mantu.advance;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/**
 * blog http://www.cnblogs.com/mantu/
 * github https://github.com/mantuliu/
 * @author mantu
 *
 */
public class Lesson5ConditionSignalAll implements Runnable{

    public static ReentrantLock lock = new ReentrantLock();
    public static Condition con = lock.newCondition();
    public static void main(String [] args){
        for(int i=0;i<30;i++){
            Thread thread = new Thread(new Lesson5ConditionSignalAll());
            thread.start();
        }
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            lock.lock();
            con.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            lock.unlock();
        }
    }
    @Override
    public void run() {
        try {
            lock.lock();
            System.out.println("thread "+ Thread.currentThread().getId() + " 开始等待");
            con.await();
            System.out.println("thread "+ Thread.currentThread().getId() + " 等待完毕");
            Thread.sleep(1000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            lock.unlock();
        }
    }
}
