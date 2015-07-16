package ch02.ex02_05;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class RandomGenerator {

	public LongStream generate(long seed){
		final long a = 25214903917L;
	    final long c = 11;
	    final long m = 1 << 48;
	    return LongStream.iterate(seed, x -> (a*x + c) % m);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomGenerator rand = new RandomGenerator();
		rand.generate(1).limit(100).forEach(System.out::println);
	}

}
