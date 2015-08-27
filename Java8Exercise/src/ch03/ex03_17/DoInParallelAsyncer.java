package ch03.ex03_17;

import java.util.function.Consumer;

public class DoInParallelAsyncer {
	
	public static Runnable handle(Runnable r, Consumer<Throwable> handler){
		return () -> {
			try{
				r.run();
			} catch (Throwable t) {
				handler.accept(t);
			}
		};
	}
	
	public static void doInParallelAsync(Runnable first, Runnable second, Consumer<Throwable> handler){
		new Thread(handle(first, handler)).start();
		new Thread(handle(second, handler)).start();
	}
}
