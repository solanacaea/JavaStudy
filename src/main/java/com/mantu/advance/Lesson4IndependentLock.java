package com.mantu.advance;

import java.util.HashMap;

/**
 * blog http://www.cnblogs.com/mantu/
 * github https://github.com/mantuliu/
 * @author mantu
 *
 */
public class Lesson4IndependentLock implements Runnable{
    String custName;//挂号人姓名
    String doctorId;//医生id
    String other;//其它附属信息
    
    public static HashMap<String,StringBuffer> orderMap = new HashMap<String,StringBuffer>();
    public static HashMap<String,String> orderYanzhengMap = new HashMap<String,String>();
    
    public static void main(String args[]){
        for(int i =0;i<3;i++){
            Lesson4IndependentLock lock1 =  new Lesson4IndependentLock("王小二", "123456", "test");
            new Thread(lock1).start();
            Lesson4IndependentLock lock2 =  new Lesson4IndependentLock("张小三", "123456", "test");
            new Thread(lock2).start();
        }
    }
    
    public Lesson4IndependentLock(String custName,String doctorId,String other){
        this.custName=custName;
        this.doctorId=doctorId;
        this.other=other;
    }
    public void addOrder(String custName,String doctorId,String other){
        StringBuffer lockString = null;
        if(orderMap.containsKey(custName+"&"+other)){
            lockString=orderMap.get(custName+"&"+other);
        }
        else{
            synchronized(orderMap){
                if(!orderMap.containsKey(custName+"&"+other)){
                    lockString = new StringBuffer(custName+"&"+other);
                    orderMap.put(custName+"&"+other, lockString);
                }
                else{
                    lockString=orderMap.get(custName+"&"+other);
                }
            }
        }
        synchronized(lockString){
            if(orderYanzhengMap.containsKey(custName+"&"+other)){
                System.out.println(custName+"之前已经下过"+doctorId+"的预约单，不能重复下单");
            }
            else{
                orderYanzhengMap.put(custName+"&"+other, custName+"&"+other);
                System.out.println(custName+"刚刚下了一个"+doctorId+"的预约单");
            }
        }
    }
    @Override
    public void run() {
        addOrder(custName,doctorId,other);
    }
}
