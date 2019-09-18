package com.mantu.advance;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/**
 * blog http://www.cnblogs.com/mantu/
 * github https://github.com/mantuliu/
 * @author mantu
 *
 */
public class Lesson5ConditionAwaitInterrupt implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition con = lock.newCondition();
    public static void main(String [] args) throws InterruptedException{
        Thread thread = new Thread(new Lesson5ConditionAwaitInterrupt());
        thread.start();
        Thread.sleep(5000L);//当前线程睡5秒
        System.out.println("开始中断线程");
        thread.interrupt();//中断线程
    }

    @Override
    public void run() {
        try{
            lock.lock();
            while(true){
                System.out.println("开始等待signal的通知");
                con.await();
                //con.awaitUninterruptibly();
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            lock.unlock();
        }
    }
}
