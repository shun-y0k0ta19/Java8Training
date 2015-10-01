package ch06.ex01;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

public class LongestCharsFindTest {
	
	@Test
	public void test() {
		LongestCharsFinder lcf = new LongestCharsFinder();
		try {
			String longestWord = lcf.findMaxCharsWord("test/ch06/ex01/war-and-peace.txt");
			assertEquals("characteristically", longestWord);
		} catch (IOException | InterruptedException | TimeoutException e) {
			e.printStackTrace();
		}
	}

}
