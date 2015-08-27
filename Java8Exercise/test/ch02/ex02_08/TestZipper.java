package ch02.ex02_08;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

public class TestZipper {

	private static final int STRING_NUM = 100;
	private List<String> first = new ArrayList<>();
	private List<String> second = new ArrayList<>();
	
	
	@Test
	public void testSameLengthStream() {
		final int length = 5;
		List<String> zipped = new ArrayList<>();
		for(int i = 0; i < STRING_NUM; i++){
			first.add("first" + i);
			second.add("second" + i);
		}
		for(int i = 0; i < length; i++){
			zipped.add(first.get(i));
			zipped.add(second.get(i));
		}
		
		Stream<String> result = Zipper.zip(first.stream().limit(length), second.stream().limit(length));
		result.forEach(System.out::println);
		
	}

}
