package com.mantu.advance;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;


/**
 * blog http://www.cnblogs.com/mantu/
 * github https://github.com/mantuliu/
 * @author mantu
 *
 */
public class Lesson8LockIntPerform {
    public static ReentrantLock lock = new ReentrantLock();
    public static int addOrderCount =0;
    public static long beginTime = System.currentTimeMillis();
    public static long yunxingTime =60000L;
    public static void main(String[] args){
        //ArrayList <Thread>orders= new ArrayList(100);
        for(int i=0;i<100;i++){
            Thread lock = new Thread(new OrderLock());
            //orders.add(lock);
            lock.start();
        }
        while(true){
            long nowTime = System.currentTimeMillis();
            if((nowTime-beginTime)>yunxingTime){
                System.out.println("在使用ReentrantLock加非公平锁的情况下100个线程循环下单数为："+Lesson8LockIntPerform.addOrderCount);
                break;
            }
            else{
                try {
                    Thread.currentThread().sleep(1000L);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}

class OrderLock implements Runnable{

    @Override
    public void run() {
        while(true){
            if((System.currentTimeMillis()-Lesson8LockIntPerform.beginTime)>Lesson8LockIntPerform.yunxingTime)
            {
                break;
            }
            addOrder();
        }
    }
    
    public void addOrder(){
        //模拟下单了
        try{
            Lesson8LockIntPerform.lock.lock();
            Lesson8LockIntPerform.addOrderCount++;//统计下单次数
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            Lesson8LockIntPerform.lock.unlock();
        }
    }
}
