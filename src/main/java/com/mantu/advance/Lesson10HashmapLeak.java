package com.mantu.advance;

import java.util.HashMap;


/**
 * blog http://www.cnblogs.com/mantu/
 * github https://github.com/mantuliu/
 * @author mantu
 *
 */
public class Lesson10HashmapLeak {

    public static void main(String[] args){
        testHashMapNormal();
        testHashMapBug();
    }
    
    public static void testHashMapBug(){
        HashMap<String,String> map = new HashMap<String,String>(100000);
        String xxx= asciiToString("0");
        String temp = xxx;
        long beginTime = System.currentTimeMillis();
        //System.out.println("开始时间："+System.currentTimeMillis());
        for(int i=0;i<100000;i++){
            map.put(xxx, i+"");

            if((i%10000)==0){
                xxx=temp;
            }
            else{
                xxx=xxx+temp;
            }
        }
        System.out.println("testHashMapBug()耗时："+(System.currentTimeMillis()-beginTime)+"毫秒");
    }
    
    public static void testHashMapNormal(){
        HashMap<String,String> map = new HashMap<String,String>(100000);
        String xxx= asciiToString("1");
        String temp = xxx;
        long beginTime = System.currentTimeMillis();
        //System.out.println("开始时间："+System.currentTimeMillis());
        for(int i=0;i<100000;i++){
            map.put(xxx, i+"");

            if((i%10000)==0){
                xxx=temp;
            }
            else{
                xxx=xxx+temp;
            }
        }
        System.out.println("testHashMapNormal()耗时："+(System.currentTimeMillis()-beginTime)+"毫秒");
    }
    public static String asciiToString(String value)
    {   
        StringBuffer sbu = new StringBuffer();
        String[] chars = value.split(",");
        for (int i = 0; i < chars.length; i++) {
            sbu.append((char) Integer.parseInt(chars[i]));
        }
        return sbu.toString();
    }
}
