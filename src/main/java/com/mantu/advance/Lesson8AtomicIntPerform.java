package com.mantu.advance;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * blog http://www.cnblogs.com/mantu/
 * github https://github.com/mantuliu/
 * @author mantu
 *
 */
public class Lesson8AtomicIntPerform {
    public static AtomicInteger addOrderCount =new AtomicInteger(0);
    public static long beginTime = System.currentTimeMillis();
    public static long yunxingTime =60000L;
    public static void main(String[] args){
        //ArrayList <Thread>orders= new ArrayList(100);
        for(int i=0;i<100;i++){
            Thread lock = new Thread(new Atomic());
            //orders.add(lock);
            lock.start();
        }
        while(true){
            long nowTime = System.currentTimeMillis();
            if((nowTime-beginTime)>yunxingTime){
                System.out.println("在使用AtomicInteger的情况下100个线程循环下单数为："+Lesson8AtomicIntPerform.addOrderCount.get());
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

class Atomic implements Runnable{

    @Override
    public void run() {
        while(true){
            if((System.currentTimeMillis()-Lesson8AtomicIntPerform.beginTime)>Lesson8AtomicIntPerform.yunxingTime)
            {
                break;
            }
            addOrder();
        }
    }
    
    public void addOrder(){
        //模拟下单了
        Lesson8AtomicIntPerform.addOrderCount.incrementAndGet();//统计下单次数
    }
}
