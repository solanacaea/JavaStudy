package java8.collection;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.IntStream;

public class QueueSample {

	public static void main(String[] args) {
//		queueSample();//FIFO
//		priorityQueueSample();
//		priorityBlockingQueue();
		arrayBlockingQueue();
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
    
    private static void priorityQueueSample() {

		PriorityQueue<String> pq = new PriorityQueue<>();
		pq.add("c");
		pq.add("d");
		pq.add("b");
		pq.offer("a");
		pq.offer("c");
		pq.offer("a");
		System.out.println(pq);//the order? 
//		System.out.println("peek: " + pq.peek());
//		System.out.println("poll: " + pq.poll());
		boolean removed = pq.remove("a");//which c is removed?
		System.out.println(removed);
		System.out.println(pq);
    }
    
    private static void priorityBlockingQueue() {
    	
    	PriorityBlockingQueue<String> q = new PriorityBlockingQueue<>();
    	q.put("123");
    	try {
			System.out.println(q.take());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	q.offer("abc");
    	System.out.println(q.toString());
//    	q.drainTo(null);
    }
    
    private static void arrayBlockingQueue() {
    	ArrayBlockingQueue<String> q = new ArrayBlockingQueue<>(20);
    	IntStream.range(0, 20).boxed().forEach(i -> q.offer(i.toString()));
//    	System.out.println(q.add("a"));
//    	System.out.println(q.offer("a"));
    	try {
			q.put("a");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}

