package com.mantu.advance;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;


/**
 * blog http://www.cnblogs.com/mantu/
 * github https://github.com/mantuliu/
 * @author mantu
 *
 */
public class Lesson3WriteReadLock {

    public static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public static int orderCount=0;//订单数量
    public static int orderPrice=0;//已收金额
    public static void main(String [] args) throws InterruptedException{
        int  count = 0;
        while(true){
            count++;
            Thread.currentThread().sleep(100L);
            new Thread(new ReadThread()).start();
            if((count%10)==0){
                new Thread(new WriteThread()).start();
                System.out.println("当前hold住的读锁的数量是："+lock.getReadHoldCount());
                System.out.println("当前hold住的写锁的数量是："+lock.getWriteHoldCount());
            }
        }
    }
}

class ReadThread implements Runnable{
    
    @Override
    public void run() {
        ReadLock readLock = Lesson3WriteReadLock.lock.readLock();
        try{
            readLock.lock();
            readOrder();
            Thread.currentThread().sleep(1000L);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            readLock.unlock();
        }
    }
    
    public void readOrder(){
        System.out.println("当前执行的读线程是："+Thread.currentThread().getId()+" 订单数是："+Lesson3WriteReadLock.orderCount);       
    }
    
}

class WriteThread implements Runnable{

    @Override
    public void run() {
        WriteLock writeLock = Lesson3WriteReadLock.lock.writeLock();
        try{
            writeLock.lock();
            writeOrder();
            Thread.currentThread().sleep(1000L);
        }
        catch(Exception ex){
            
        }
        finally{
            writeLock.unlock();
        }
    }
    
    public void writeOrder(){
        Lesson3WriteReadLock.orderCount++;
        System.out.println("当前执行的写线程是："+Thread.currentThread().getId());
    }
}
