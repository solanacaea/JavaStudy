package com.mantu.advance;


/**
 * blog http://www.cnblogs.com/mantu/
 * github https://github.com/mantuliu/
 * @author mantu
 *
 */
public class Lesson6InterruptSync {
    public static void main(String args []){
        Thread one = new Thread(new ThreadSync());
        Thread two = new Thread(new ThreadSync());
        one.start();
        two.start();
        try {
            Thread.currentThread().sleep(1000L);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        one.interrupt();
        two.interrupt();
    }
}

class ThreadSync implements Runnable{

    @Override
    public void run() {
        synchronized(Lesson6InterruptSync.class){
            System.out.println("thread 's "+Thread.currentThread().getId()+"拿到了对象锁，线程开始挂起");
            Thread.currentThread().suspend();
        }
    }
    
}
