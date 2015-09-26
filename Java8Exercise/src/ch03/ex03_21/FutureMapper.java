package ch03.ex03_21;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

import ch03.ex03_20.CopyOfPerson;
import ch03.ex03_20.Person;

public class FutureMapper {
	
	/**
	 * 指定された関数をFuture<T>に適用した結果から構成されるFuture<U>を返します。
	 * @param future　mapperを適用するFutureインスタンス
	 * @param mapper 各要素に適用する非干渉でステートレスな関数
	 * @return
	 */
	public static <T, U> Future<U> map(Future<T> future, Function<T, U> mapper){
		return new Future<U>(){

			@Override
			public boolean cancel(boolean mayInterruptIfRunning) {
				return future.cancel(mayInterruptIfRunning);
			}

			@Override
			public boolean isCancelled() {
				return future.isCancelled();
			}

			@Override
			public boolean isDone() {
				return future.isDone();
			}

			@Override
			public U get() throws InterruptedException, ExecutionException {
				return mapper.apply(future.get());
			}

			@Override
			public U get(long timeout, TimeUnit unit)
					throws InterruptedException, ExecutionException,
					TimeoutException {
				return mapper.apply(future.get(timeout, unit));
	        }
		};
	}
	
	public static void main(String[] args){
		List<Person> pList = new ArrayList<Person>();	
		pList.add(new Person("John" , 20));
		pList.add(new Person("Mark", 22));
		pList.add(new Person("Peter", 27));
		List<Future<CopyOfPerson>> copFutures = new ArrayList<>();
		
		ExecutorService es = Executors.newFixedThreadPool(3);
		for(Person p : pList){
			copFutures.add(es.submit(() -> new CopyOfPerson(p)));
		}
		for(Future<CopyOfPerson> f : copFutures){
			try {
				System.out.println(f.get().toString());
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		es.shutdown();
	}
}
