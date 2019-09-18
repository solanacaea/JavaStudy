package com.mantu.advance;

import java.util.ArrayList;


/**
 * blog http://www.cnblogs.com/mantu/
 * github https://github.com/mantuliu/
 * @author mantu
 *
 */
public class Lesson8SyncIntPerform {

    public static int addOrderCount =0;
    public static long beginTime = System.currentTimeMillis();
    public static long yunxingTime =60000L;
    public static void main(String[] args){
        for(int i=0;i<100;i++){
            Thread lock = new Thread(new OrderSync());
            lock.start();
        }
        while(true){
            long nowTime = System.currentTimeMillis();
            if((nowTime-beginTime)>yunxingTime){
                System.out.println("在使用同步关键字加锁的情况下100个线程循环下单数为："+Lesson8SyncIntPerform.addOrderCount);
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

class OrderSync implements Runnable{

    @Override
    public void run() {
        while(true){
            if((System.currentTimeMillis()-Lesson8SyncIntPerform.beginTime)>Lesson8SyncIntPerform.yunxingTime)
            {
                break;
            }
            addOrder();
        }
    }
    
    public void addOrder(){
        //模拟下单了
        synchronized(Lesson8SyncIntPerform.class){
            Lesson8SyncIntPerform.addOrderCount++;//统计下单次数
        }
    }
}
