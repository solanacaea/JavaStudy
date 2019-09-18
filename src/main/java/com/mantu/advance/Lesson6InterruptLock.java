package com.mantu.advance;

import java.util.concurrent.locks.ReentrantLock;


/**
 * blog http://www.cnblogs.com/mantu/
 * github https://github.com/mantuliu/
 * @author mantu
 *
 */
public class Lesson6InterruptLock implements Runnable{
    public static ReentrantLock lock = new ReentrantLock();

    public static void main(String [] args){
        Thread one = new Thread(new Lesson6InterruptLock());
        Thread two = new Thread(new Lesson6InterruptLock());
        one.start();
        try {
            Thread.currentThread().sleep(1000L);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        two.start();
        two.interrupt();
    }
    @Override
    public void run() {
        try{
            lock.lockInterruptibly();
            System.out.println("thread's "+Thread.currentThread().getId()+" 已经拿到了锁");
            Thread.currentThread().sleep(10000L);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            System.out.println("thread's "+Thread.currentThread().getId()+" 执行完毕");
            lock.unlock();
        }
    }
}
