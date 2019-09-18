package com.mantu.advance;


/**
 * blog http://www.cnblogs.com/mantu/
 * github https://github.com/mantuliu/
 * @author mantu
 *
 */
public class Lesson6ThreadStopDeadLock extends Thread{

    public static void main(String[] args){
        Thread one = new Lesson6ThreadStopDeadLock();
        one.start();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        one.stop();
    }
    @Override
    public void  run() {
        try{
            synchronized(this){
                System.out.println("thread 's "+Thread.currentThread().getId()+"拿到了对象锁，线程开始挂起");
                Thread.currentThread().suspend();
                System.out.println("thread 's "+Thread.currentThread().getId()+"线程挂起结束");
            }
        }
        finally{
            System.out.println("thread 's "+Thread.currentThread().getId()+"结束了");
        }
        
    }
    
}
