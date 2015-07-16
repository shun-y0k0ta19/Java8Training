package ch02.ex02_07;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamFiniteChecker {

	public static <T> boolean isFinite(Stream<T> stream) {
	    long length = stream.spliterator().getExactSizeIfKnown();
	    return (length != -1);
	}
	
	public static Stream generate(long seed){
		final Long a = 25214903917L;
	    final Long c = 11L;
	    final Long m = 1L << 48;
	    return Stream.iterate(seed, x -> (a*x + c) % m);
		
	}

	public static void main(String[] args) {
		Integer[] values = {1, 4, 9, 16};
		System.out.println(isFinite(Stream.of(values)));
		System.out.println(isFinite(generate(1)));
		//終端処理をしなければならないため、チェックしたあとのStreamは使えないため、
		//あまり意味が無い
	}

}
