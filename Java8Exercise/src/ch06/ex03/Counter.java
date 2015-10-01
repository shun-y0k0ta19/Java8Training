package ch06.ex03;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;

public class Counter {
	
	/**
	 * 各スレッド100,000回のインクリメントを1000のスレッドで行う。
	 * @param counter インクリメントをカウントするカウンター
	 * @param incrementer インクリメントの方法を示す関数インターフェース
	 * @return インクリメントを終えたカウンター
	 * @throws InterruptedException
	 * @throws TimeoutException
	 */
	private <T> T countAtomic(T counter, Consumer<T> incrementer) throws InterruptedException, TimeoutException{
		final int THREADS = 1000;
		final int COUNTS = 100000;
		
		ExecutorService es = Executors.newFixedThreadPool(THREADS);
		CountDownLatch latch = new CountDownLatch(THREADS);
		for(int i = 0; i < THREADS; i++){
			es.submit(() ->{
				for(int count = 0; count < COUNTS; count++){
					incrementer.accept(counter);
				}
			});
			latch.countDown();
		}
		latch.await();
		es.shutdown();
		if(es.awaitTermination(10, TimeUnit.SECONDS)){
			return counter;
		}
		throw new TimeoutException("countWithAtomicLong() is Time out!");
	}
	
	/**
	 * AtomicLongを利用して1000個のスレッドでそれぞれ100,000回のインクリメントを行う。
	 * @return　インクリメントを行った結果
	 * @throws InterruptedException
	 * @throws TimeoutException
	 */
	public long countWithAtomicLong() throws InterruptedException, TimeoutException{
		AtomicLong atomicLongCounter = new AtomicLong();	
		countAtomic(atomicLongCounter, alCounter -> alCounter.incrementAndGet());
		return atomicLongCounter.get();
	}
	
	/**
	 * LongAdderを利用して1000個のスレッドでそれぞれ100,000回のインクリメントを行う。
	 * @return　インクリメントを行った結果
	 * @throws InterruptedException
	 * @throws TimeoutException
	 */
	public long countWithLongAdder() throws InterruptedException, TimeoutException{
		LongAdder longAdderCounter = new LongAdder();
		countAtomic(longAdderCounter, laCounter -> laCounter.increment());
		return longAdderCounter.sum();
	}

	public static void main(String[] args) {
		Counter counter = new Counter();
		long start, end;

		try {
			//AtomicLongのカウント
			start = System.nanoTime();
			counter.countWithAtomicLong();
			end = System.nanoTime();
			System.out.printf("AtomicLong: %11d ns%n", end - start);

			//LongAdderでのカウント
			start = System.nanoTime();
			counter.countWithLongAdder();
			end = System.nanoTime();
			System.out.printf("LongAdder : %11d ns%n", end - start);
		} catch (InterruptedException | TimeoutException e) {
			e.printStackTrace();
		}
	}
}

/*
 * result
 * AtomicLong:  2844151908 ns
 * LongAdder :   546360608 ns
 */
