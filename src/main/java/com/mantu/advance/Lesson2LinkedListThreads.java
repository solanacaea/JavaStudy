package com.mantu.advance;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;


/**
 * blog http://www.cnblogs.com/mantu/
 * github https://github.com/mantuliu/
 * @author mantu
 *
 */
public class Lesson2LinkedListThreads {
    public static LinkedList list = new LinkedList();
    //public static ConcurrentLinkedQueue list = new ConcurrentLinkedQueue();
    public static void main(String [] args) throws InterruptedException{
        for(int i=0;i<100;i++){
            new Thread(new ListPut()).start();
        }
        Thread.currentThread().sleep(3000L);
        System.out.println("List的变量size值为："+list.size());
        for(int i=0;i<list.size();i++){
            String temp = (String) list.poll();
            if(temp==null){
                System.out.println("第"+i+"个元素取出为null");
                return;
            }
        }
    }
    
}

class ListPut implements Runnable{

    @Override
    public void run() {
        //synchronized(ListPut.class){
            for(int i=0;i<1000;i++){
                Lesson2LinkedListThreads.list.add("");
            }
        //}
        //System.out.println("线程"+Thread.currentThread().getId()+"已经结束了");
    }
    
}