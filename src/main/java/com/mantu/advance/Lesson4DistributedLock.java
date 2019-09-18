package com.mantu.advance;

import redis.clients.jedis.Jedis;


/**
 * blog http://www.cnblogs.com/mantu/
 * github https://github.com/mantuliu/
 * @author mantu
 *
 */
public class Lesson4DistributedLock implements Runnable{

    String custName;//挂号人姓名
    String doctorId;//医生id
    String other;//其它附属信息
    
    public static void main(String [] args){
        for(int i =0;i<3;i++){
            Lesson4DistributedLock lock1 =  new Lesson4DistributedLock("王小二", "123456", "test");
            new Thread(lock1).start();
            Lesson4DistributedLock lock2 =  new Lesson4DistributedLock("张小三", "123456", "test");
            new Thread(lock2).start();
        }
    }
    
    public Lesson4DistributedLock(String custName,String doctorId,String other){
        this.custName=custName;
        this.doctorId=doctorId;
        this.other=other;
    }
    public void addOrder(String custName,String doctorId,String other){
        
        try{
            boolean lock = JedisUtil.trylock(custName+"&"+doctorId);
            if(lock==false){
                System.out.println("线程"+Thread.currentThread().getId()+":"+custName+"&"+doctorId+"的锁已经被其它线程拿到，请过几秒后再下单");
            }
            else{
                System.out.println("线程"+Thread.currentThread().getId()+":"+custName+"&"+doctorId+"已经拿到锁并成功下单");
                Thread.currentThread().sleep(3000L);
                JedisUtil.unlock(custName+"&"+doctorId);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    @Override
    public void run() {
        addOrder(custName,doctorId,other);
    }
}

class JedisUtil {

    public static Jedis createJedis() {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        return jedis;
    }
    
    public static boolean trylock(String key){
        Jedis jedis = JedisUtil.createJedis();
        boolean flag = false;
        try{
            if(jedis.setnx(key,"")>0){
                jedis.expire(key, 5);//5秒过期
                flag =true;
            }
        }
        catch(Exception ex){
            unlock(key);
            flag = false;
        }
        finally{
            jedis.close();
            jedis = null;
        }
        return flag;
    }
    
    public static void unlock(String key){
        Jedis jedis = JedisUtil.createJedis();
        try{
            jedis.del(key);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            jedis.close();
            jedis=null;
        }
    }
}
