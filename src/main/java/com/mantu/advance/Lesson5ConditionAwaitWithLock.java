package com.mantu.advance;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/**
 * blog http://www.cnblogs.com/mantu/
 * github https://github.com/mantuliu/
 * @author mantu
 *
 */
public class Lesson5ConditionAwaitWithLock implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition con = lock.newCondition();
    
    public static void main(String [] args) throws InterruptedException {
        Thread thread = new Thread(new Lesson5ConditionAwaitWithLock());
        thread.start();
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        con.signal();
        con.await();
        
    }
    @Override
    public void run() {
        try {
            lock.lock();
            con.signal();
            System.out.println("通知信号发送完毕");
            con.await();
            System.out.println("接收到通知信号");
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            lock.unlock();
        }
    }

}
