package java8.collection;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomAccessSample {

	public static void main(String[] args) {

		List arrList = IntStream.rangeClosed(0, 50000).boxed().collect(Collectors.toList());
		List linkList = IntStream.rangeClosed(0, 50000).boxed().collect(LinkedList::new, LinkedList::add, LinkedList::addAll);
		
		long time1 = arrayListFor(arrList);
		long time2 = arrayListIterator(arrList);
		long time3 = linkedListFor(linkList);
		long time4 = linkedListIterator(linkList);
		System.out.println("ArrayList for loop time cost=" + time1);
		System.out.println("ArrayList iterator time cost=" + time2);
		System.out.println("LinkedList for loop time cost=" + time3);
		System.out.println("LinkedList iterator time cost=" + time4);
	}
	
	public static long arrayListFor(List list) {
		long start = System.currentTimeMillis();
		for (int i = 0; i < list.size(); i++) {
			Object o = list.get(i);
		}
		long end = System.currentTimeMillis();
		return end - start;
	}
	
	public static long arrayListIterator(List list) {
		long start = System.currentTimeMillis();
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Object o = iter.next();
		}
		long end = System.currentTimeMillis();
		return end - start;
	}
	
	public static long linkedListFor(List list) {
		long start = System.currentTimeMillis();
		for (int i = 0; i < list.size(); i++) {
			Object o = list.get(i);
		}
		long end = System.currentTimeMillis();
		return end - start;
	}
	
	public static long linkedListIterator(List list) {
		long start = System.currentTimeMillis();
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Object o = iter.next();
		}
		long end = System.currentTimeMillis();
		return end - start;
	}
	
}
