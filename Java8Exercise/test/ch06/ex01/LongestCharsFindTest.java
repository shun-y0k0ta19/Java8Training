package ch06.ex01;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

public class LongestCharsFindTest {
	
	/**
	 * 読み込んだファイルの中から最大文字数の単語を抽出できるか
	 */
	@Test
	public void testFindMaxChars() {
		LongestCharsFinder lcf = new LongestCharsFinder();
		try {
			String longestWord = lcf.findMaxCharsWord("test/FilesForTests/MyUtilities/war-and-peace.txt");
			assertEquals("characteristically", longestWord);
		} catch (IOException | InterruptedException | TimeoutException e) {
			e.printStackTrace();
		}
	}
}
