package com.mantu.advance;


/**
 * blog http://www.cnblogs.com/mantu/
 * github https://github.com/mantuliu/
 * @author mantu
 *
 */
public class Lesson7ThreadsPerform {

    public static void main(String [] args){
        long beginTime = System.currentTimeMillis();
        Thread monitor = new Thread(new Runnable(){
            @Override
            public void run() {
                while(true){
                    System.out.println("当前的活动线程数："+Thread.activeCount());
                    System.out.println("北大医院的下单次数："+ThreadBeidaYiyuan.diaoyongCount.get());
                    System.out.println("儿童医院的下单次数："+ThreadErtongYiyuan.diaoyongCount.get());
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            
        });
        monitor.start();
        while(true){
            long nowTime = System.currentTimeMillis();
            if((nowTime-beginTime)>60000){
                System.out.println("北大医院和儿童医院在一分钟内的下单次数："+(ThreadBeidaYiyuan.diaoyongCount.get()+ThreadErtongYiyuan.diaoyongCount.get()));
                break;
            }
            new Thread(new ThreadBeidaYiyuan()).start();
            new Thread(new ThreadErtongYiyuan()).start();
        }
        monitor.stop();
    }
}
