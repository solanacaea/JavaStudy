package java8.concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class FairLockSample {
	public static ReentrantLock lock = new ReentrantLock(true);//��ƽ��
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
				System.out.println("��ʹ��ReentrantLock�ӹ�ƽ���������100���߳�ѭ���µ���Ϊ��"+FairLockSample.addOrderCount);
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
			FairLockSample.addOrderCount++;//ͳ���µ�����
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			FairLockSample.lock.unlock();
		}
	}
}