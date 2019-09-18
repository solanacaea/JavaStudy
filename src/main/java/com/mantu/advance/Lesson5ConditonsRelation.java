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
public class Lesson5ConditonsRelation{
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition conOne = lock.newCondition();
    public static Condition conTwo = lock.newCondition();
    public static void main(String [] args){
        for(int i=0;i<3;i++){
            new Thread(new UsedConditionOne()).start();
            new Thread(new UsedConditionTwo()).start();
        }
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            lock.lock();
            Lesson5ConditonsRelation.conOne.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            lock.unlock();
        }
        
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            lock.lock();
            Lesson5ConditonsRelation.conTwo.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            lock.unlock();
        }
    }
}
class UsedConditionOne implements Runnable{

    @Override
    public void run() {
        Lock lock = Lesson5ConditonsRelation.lock;
        try {
            lock.lock();
            System.out.println("UsedConditionOne 's thread "+ Thread.currentThread().getId() + " 开始等待");
            Lesson5ConditonsRelation.conOne.await();
            System.out.println("UsedConditionOne 's thread "+ Thread.currentThread().getId() + " 等待完毕");
            Lesson5ConditonsRelation.conOne.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            lock.unlock();
        }
    }
    
}
class UsedConditionTwo implements Runnable{

    @Override
    public void run() {
        Lock lock = Lesson5ConditonsRelation.lock;
        try {
            lock.lock();
            System.out.println("UsedConditionTwo 's thread "+ Thread.currentThread().getId() + " 开始等待");
            Lesson5ConditonsRelation.conTwo.await();
            System.out.println("UsedConditionTwo 's thread "+ Thread.currentThread().getId() + " 等待完毕");
            Lesson5ConditonsRelation.conTwo.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            lock.unlock();
        }
    }
    
}

