package com.mantu.advance;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * blog http://www.cnblogs.com/mantu/
 * github https://github.com/mantuliu/
 * @author mantu
 *
 */
public class Lesson7ThreadsOnly {
    
    public static void main(String[] args){
        new Thread(new Runnable(){

            @Override
            public void run() {
                while(true){
                    System.out.println("当前的活动线程数："+Thread.activeCount());
                    System.out.println("南山医院的下单次数："+ThreadNanshanYiyuan.diaoyongCount.get());
                    System.out.println("北大医院的下单次数："+ThreadBeidaYiyuan.diaoyongCount.get());
                    System.out.println("儿童医院的下单次数："+ThreadErtongYiyuan.diaoyongCount.get());
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            
        }).start();
        int count=0;
        while(true){
            count++;
            /*
            if((count%300)==1){
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            */
            new Thread(new ThreadNanshanYiyuan()).start();
            new Thread(new ThreadBeidaYiyuan()).start();
            new Thread(new ThreadErtongYiyuan()).start();
        }
    }
}

//南山医院，模拟网络中断了
class ThreadNanshanYiyuan implements Runnable{

    public static AtomicInteger diaoyongCount = new AtomicInteger(0);
    @Override
    public void run() {
        try {
            diaoyongCount.incrementAndGet();
            Thread.sleep(10000L);//线程睡10秒，模拟10秒超时
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}

//北大医院
class ThreadBeidaYiyuan implements Runnable{
    public static AtomicInteger diaoyongCount = new AtomicInteger(0);
    @Override
    public void run() {
        diaoyongCount.incrementAndGet();
        /*
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        */
    }
    
}

//儿童医院
class ThreadErtongYiyuan implements Runnable{
    public static AtomicInteger diaoyongCount = new AtomicInteger(0);
    @Override
    public void run() {
        diaoyongCount.incrementAndGet();
        /*
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        */
    }
    
}
