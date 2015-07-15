package ch01.ex01_06;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class useRunnableEx {
	/**
	 * Runnable内で処理しなければならないチェックされる例外をキャッチし、チェックされない例外をスローする処理に変える。
	 * @param runner　Runnableとして処理を行わせたい内容(関数インターフェース)
	 * @return runnerのrunメソッドを実行し、チェックされる例外をキャッチした場合、チェックされない例外としてスローするRunnableインターフェースを返す。
	 */
	public static Runnable uncheck(RunnableEx runner){
		System.out.println("uncheck ThreadID is " + Thread.currentThread().getId());
		Runnable r = () ->{
			try {
				System.out.println("try/catch ThreadID is " + Thread.currentThread().getId());
				runner.run();
			} catch (Exception e) {
				throw new RuntimeException("wrapped uncheck", e);
			}
		};
		return r;
	}

	/**
	 * RunnableExの代わりにCallableを使って実装したuncheckメソッド
	 * @param caller
	 * @return
	 */
	public static Runnable uncheckCallable(Callable<Runnable> caller){
		System.out.println("uncheckCallable ThreadID is " + Thread.currentThread().getId());
		ExecutorService es = Executors.newCachedThreadPool();
		try {
			System.out.println("try/catch ThreadID is " + Thread.currentThread().getId());
			return es.submit(caller).get();
			//return caller.call();
		} catch (Exception e) {
			throw new RuntimeException();
		}

	}

	public static void main(String[] args) {
		//System.out.println("main ThreadID is " + Thread.currentThread().getId());

		new Thread(uncheck(() -> {
			System.out.println("run ThreadID is " + Thread.currentThread().getId());
			System.out.println("Zzz");
			Thread.sleep(1000);
		})).start();
		
		new Thread(uncheckCallable(() -> {
			System.out.println("runnable ThreadID is " + Thread.currentThread().getId());
			return () -> {
				System.out.println("call ThreadID is " + Thread.currentThread().getId());
				System.out.println("Zzz");
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		})).start();

	}

}
