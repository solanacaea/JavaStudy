package com.mantu.advance;

import java.util.LinkedList;
import java.util.Collection;
import java.util.HashMap;



/**
 * blog http://www.cnblogs.com/mantu/
 * github https://github.com/mantuliu/
 * @author mantu
 *
 */
public class Lesson11Generic {

    public static void main(String [] args){
        Lesson11Generic lg = new Lesson11Generic();
        lg.testGeneric();
    }
    
    public void testHashMapGeneric(){
        HashMap<Parent,Parent> bufferMap = new HashMap<Parent,Parent>();
        String str = "xiaodaoliu";
        Parent pa = new Parent(str);
        Child chi = new Child(str);
        bufferMap.put(pa, pa);
        bufferMap.put(chi, chi);
        System.out.println(bufferMap.get(pa).hashcode());
        System.out.println(bufferMap.get(chi).hashcode());
    }
    
    public void testGeneric(){
        GenericTest<Parent,Parent,Parent>gt = new GenericTest<Parent,Parent,Parent>();
        HashMap<Parent,Parent> chiMap = new HashMap<Parent,Parent>();
        String str = "xiaodaoliu";
        Child chi = new Child(str);
        Parent pa = new Parent(str);
        gt.println(chi, chi, chi);
        gt.printlnd(chiMap);
    }
    
    class GenericTest<T,V,B> {
        public <T,V,B> GenericTest(){
            
        }
        
        public void println(T t,V v,B b){
            System.out.println(t.toString());
            System.out.println(v);
            System.out.println(b);
        }
        
        public void printlnd(HashMap<T,V> v){
            System.out.println("map执行了");
        }
    }
    class Parent {
        String value ="";
        public Parent(String value){
            this.value=value;
        }
        
        public int hashcode(){
            int h=0;
            char[] vchar = value.toCharArray();
            for(int i=0;i<vchar.length;i++){
                h=h*31+vchar[i];
            }
            return h;
        }
    }
    class Child extends Parent{

        public Child(String value) {
            super(value);
        }
        public int hashcode(){
            int h=21;
            char[] vchar = value.toCharArray();
            for(int i=0;i<vchar.length;i++){
                h=h*31+vchar[i];
            }
            return h;
        }
        public String toString(){
            return value;
        }
    }
}
