package ch06.ex06_03;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class IncrementCounter {

	public void atomicLongCount() throws InterruptedException{
		final int TASK = 1000;
		final int COUNT = 100_000;

		AtomicLong globalCounter = new AtomicLong(0);
		CountDownLatch latch = new CountDownLatch(TASK);
		ExecutorService pool = Executors.newCachedThreadPool();
		long startTime = System.nanoTime();
		for (int n = 0; n < TASK; n++) {
		    pool.submit(
		        () -> {
		            for (int i = 0; i < COUNT; i++) {
		                globalCounter.incrementAndGet();
		            }
		            latch.countDown();
		        }
		    );
		}
		pool.shutdown();
		latch.await();
		System.out.println("経過時間 : " + (System.nanoTime() - startTime)/1E9 + "秒");
		System.out.println("カウンタ : " + globalCounter);
	}
	
	public void longAdderCount() throws InterruptedException{
		final int TASK = 1000;
		final int COUNT = 100_000;

		LongAdder globalCounter = new LongAdder();
		CountDownLatch latch = new CountDownLatch(TASK);
		ExecutorService pool = Executors.newCachedThreadPool();
		long startTime = System.nanoTime();
		for (int n = 0; n < TASK; n++) {
		    pool.submit(
		        () -> {
		            for (int i = 0; i < COUNT; i++) {
		                globalCounter.increment();
		            }
		            latch.countDown();
		        }
		    );
		}
		pool.shutdown();
		latch.await();
		System.out.println("経過時間 : " + (System.nanoTime() - startTime)/1E9 + "秒");
		System.out.println("カウンタ : " + globalCounter);
	}
}
