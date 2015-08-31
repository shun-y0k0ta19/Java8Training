package ch02.ex02_06;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class OneSentenseCharStream {
	
	public static Stream<Character> characterStream(String s) {
	    return IntStream.range(0, s.length()).mapToObj(n -> s.charAt(n));
	}
	public static void main(String[] args) {
		characterStream("test").forEach(System.out::println);
	}

}
