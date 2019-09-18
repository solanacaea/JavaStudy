package com.mantu.advance;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * blog http://www.cnblogs.com/mantu/
 * github https://github.com/mantuliu/
 * @author mantu
 *
 */
public class Lesson7ThreadPool {

    public static void main(String [] args){
        ExecutorService nanshanPool = Executors.newFixedThreadPool(100);
        ExecutorService beidaPool = Executors.newFixedThreadPool(100);
        ExecutorService ertongPool = Executors.newFixedThreadPool(100);
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
        while(true){
            nanshanPool.execute(new ThreadNanshanYiyuan());
            beidaPool.execute(new ThreadBeidaYiyuan());
            ertongPool.execute(new ThreadErtongYiyuan());
        }
    }
    
}
