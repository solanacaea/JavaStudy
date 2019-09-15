package java8.collection;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class QueueSample {

	public static void main(String[] args) {
		queueSample();//FIFO
	}
	
	private static void queueSample() {
    	Queue<String> queue=new LinkedList<>();
    	queue.offer("Hello");
    	queue.offer("World!");
    	queue.offer("SB");
    	
    	System.out.println(queue.size());
    	for(String str : queue){
    	    System.out.printf(str + " ");
    	}
    	System.out.printf("\n");
    	
    	System.out.println(queue.size());
    	String str;
    	while((str=queue.poll()) != null){
    	    System.out.printf(str + " ");
    	}
    	System.out.println();
    	System.out.println(queue.size());
    }
    
    private static void arrayDeque() {
    	ArrayDeque<Integer> ad = new ArrayDeque<>();
    	ad.addFirst(123); //element[--head] = 123; index out of bound? 
    	
    }
    
}
