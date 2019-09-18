package java8.concurrent;

import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ReentrantLockSample {

	private ReentrantLock fairLock = new ReentrantLock(true);
	private ReentrantLock unfairLock = new ReentrantLock(false);
	
	public static void main(String[] args) {
//		fairLock();
		unfairLock();
	}

	public void testFairFail(){
		try {
			fairLock.lock();
			System.out.println(Thread.currentThread().getName() + " got the lock at " + System.currentTimeMillis());
		}finally {
			fairLock.unlock();
		}
	}
	
	private static void fairLock() {
		ReentrantLockSample fairLock = new ReentrantLockSample();
		Runnable runnable = () -> {
			System.out.println(Thread.currentThread().getName() + " starting at " + System.currentTimeMillis());
			fairLock.testFairFail();
		};
		Thread[] threadArray = new Thread[100];
		IntStream.range(0, 100).boxed().forEach(i -> {
			threadArray[i] = new Thread(runnable);
		});
		IntStream.range(0, 100).boxed().parallel().forEach(i -> threadArray[i].start());
		
	}
	
	public void testUnfairFail(){
        try {
        	unfairLock.lock();
        	System.out.println(Thread.currentThread().getName() + " got the lock at " + System.currentTimeMillis());
        }finally {
        	unfairLock.unlock();
        }
    }
	
	private static void unfairLock() {
		ReentrantLockSample nonfairLock = new ReentrantLockSample();
        Runnable runnable = () -> {
        	System.out.println(Thread.currentThread().getName() + " starting at " + System.currentTimeMillis());
            nonfairLock.testUnfairFail();
        };
        Thread[] threadArray = new Thread[100];
        IntStream.range(0, 100).boxed().forEach(i -> {
			threadArray[i] = new Thread(runnable);
		});
        IntStream.range(0, 100).boxed().parallel().forEach(i -> threadArray[i].start());
	}

	
}
