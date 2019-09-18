package java8.concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class FairLockSample {
	public static ReentrantLock lock = new ReentrantLock(true);//公平锁
	public static int addOrderCount =0;
	public static long beginTime = System.currentTimeMillis();
	public static long yunxingTime =60000L;
	public static void main(String[] args){
		//ArrayList <Thread>orders= new ArrayList(100);
		for(int i=0;i<100;i++){
			Thread lock = new Thread(new OrderLockFair());
			//orders.add(lock);
			lock.start();
		}
		while(true){
			long nowTime = System.currentTimeMillis();
			if((nowTime-beginTime)>yunxingTime){
				System.out.println("在使用ReentrantLock加公平锁的情况下100个线程循环下单数为："+FairLockSample.addOrderCount);
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

class OrderLockFair implements Runnable{

	@Override
	public void run() {
		while(true){
			if((System.currentTimeMillis()-FairLockSample.beginTime)>FairLockSample.yunxingTime)
			{
				break;
			}
			addOrder();
		}
	}

	public void addOrder(){
		try{
			FairLockSample.lock.lock();
			FairLockSample.addOrderCount++;//统计下单次数
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			FairLockSample.lock.unlock();
		}
	}
}