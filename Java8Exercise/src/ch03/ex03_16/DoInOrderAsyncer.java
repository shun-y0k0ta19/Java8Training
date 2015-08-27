package ch03.ex03_16;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class DoInOrderAsyncer {
	public static <T> void doInOrderAsync(Supplier<T> first, BiConsumer<T, Throwable> second){
		new Thread(
		        () ->  {
		            T result = null;
		            try {
		                result = first.get();
		                second.accept(result, null);
		            } catch (Throwable th) {
		                second.accept(result, th);
		            }
		        }
		    ).start();
	}
}
