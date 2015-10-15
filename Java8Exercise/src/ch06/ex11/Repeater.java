package ch06.ex11;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Repeater {

	public static <T> CompletableFuture<T> repeat(Supplier<T> action, Predicate<T> until) {
		return null;
	}
	
	private static <T> T doAsync(Supplier<T> action, Predicate<T> until) {
		T result = action.get();
		if(until.test(result)) {
			return result;
		}
		return doAsync(action, until);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
