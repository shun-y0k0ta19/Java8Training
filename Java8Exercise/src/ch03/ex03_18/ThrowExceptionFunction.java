package ch03.ex03_18;

@FunctionalInterface
public interface ThrowExceptionFunction<T, U> {
	U apply(T t) throws Exception;
}
