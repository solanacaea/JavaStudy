package com.mantu.advance;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * blog http://www.cnblogs.com/mantu/
 * github https://github.com/mantuliu/
 * @author mantu
 *
 */
public class Lesson6ThreadStopLock {
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition conOne = lock.newCondition();
    public static int state =0;
    public static void main(String args[]){
        Thread one = new Thread(new ThreadOne());
        Thread two = new Thread(new ThreadTwo());
        one.start();
        try {
            Thread.currentThread().sleep(2000L);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        two.start();
        one.stop();
    }
}

class ThreadOne implements Runnable{

    @Override
    public void run() {
        Lock lock = Lesson6ThreadStopLock.lock;
        try {
            lock.lock();
            Lesson6ThreadStopLock.state=1;//标识线程1正在执行
            System.out.println("Thread 's " +Thread.currentThread().getId()+" 拿到了锁");
            Thread.sleep(10000L);
            System.out.println("Thread 's " +Thread.currentThread().getId()+" 正常结束了");
            Lesson6ThreadStopLock.state=0;//标识线程1执行完毕
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            System.out.println("Thread 's " +Thread.currentThread().getId()+" 锁被释放了");
            lock.unlock();
        }
    }  
}

class ThreadTwo implements Runnable{

    @Override
    public void run() {
        Lock lock = Lesson6ThreadStopLock.lock;
        try {
            lock.lock();
            if(Lesson6ThreadStopLock.state==1){
                System.out.println("ThreadOne类型的线程正在执行");
            }
            else{
                System.out.println("ThreadOne类型的线程没有正在执行的");
            }
            System.out.println("Thread 's " +Thread.currentThread().getId()+" 拿到了锁");
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            lock.unlock();
        }
    }  
}
