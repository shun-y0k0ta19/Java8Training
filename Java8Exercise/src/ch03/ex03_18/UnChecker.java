package ch03.ex03_18;

import java.util.function.Function;


public class UnChecker {
	public static <T, U> Function<T, U> unchecked(ThrowExceptionFunction<T, U> tef){
		return (t) -> {
			try {
				return tef.apply(t);
			} catch (Exception e) {
				throw new RuntimeException(e);
			} catch (Throwable th) {
				throw th;
			}
		};
	}
}
