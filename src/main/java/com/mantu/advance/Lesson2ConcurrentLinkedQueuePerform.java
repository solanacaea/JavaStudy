package com.mantu.advance;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * blog http://www.cnblogs.com/mantu/
 * github https://github.com/mantuliu/
 * @author mantu
 *
 */
public class Lesson2ConcurrentLinkedQueuePerform {
    public static ConcurrentLinkedQueue list = new ConcurrentLinkedQueue();
    public static long beginTime = System.currentTimeMillis();
    public static long yunxingTime =60000L;
    public static void main(String[] args){
        for(int i=0;i<100;i++){
            Thread lock = new Thread(new CurrentLink());
            lock.start();
        }
        while(true){
            long nowTime = System.currentTimeMillis();
            if((nowTime-beginTime)>yunxingTime){
                System.out.println("在使用ConcurrentLinkedQueue的情况下100个线程循环增加的元素数为："+Lesson2ConcurrentLinkedQueuePerform.list.size());
                break;
            }
            else{
                try {
                    Thread.currentThread().sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class CurrentLink implements Runnable{

    @Override
    public void run() {
        while(true){
            if((System.currentTimeMillis()-Lesson2ConcurrentLinkedQueuePerform.beginTime)>Lesson2ConcurrentLinkedQueuePerform.yunxingTime)
            {
                break;
            }
            add();
        }
    }
    
    public void add(){
        Lesson2ConcurrentLinkedQueuePerform.list.add("");//增加元素
    }
}
