package ch02.ex02_07;

import java.util.stream.LongStream;

public class StreamFiniteChecker {

	public static <T> boolean isFinite(LongStream stream) {
	    long length = stream.spliterator().getExactSizeIfKnown();
	    return (length != -1);
	}
	
	public static LongStream generate(long seed){
		final Long a = 25214903917L;
	    final Long c = 11L;
	    final Long m = 1L << 48;
	    return LongStream.iterate(seed, x -> (a*x + c) % m);
		
	}

	public static void main(String[] args) {
		long values[] = {1, 4, 9, 16};
		System.out.println(isFinite(LongStream.of(values)));
		System.out.println(isFinite(generate(1)));
		//終端処理をしなければならないため、チェックしたあとのStreamは使えないため、
		//あまり意味が無い
	}

}
