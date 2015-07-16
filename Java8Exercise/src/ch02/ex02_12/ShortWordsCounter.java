package ch02.ex02_12;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class ShortWordsCounter {

	public void count(Stream<String> wordsStream){
		AtomicInteger[] count = new AtomicInteger[12];
		Arrays.setAll(count, n -> new AtomicInteger(0));
		wordsStream.parallel()
		    .forEach(s -> { if (s.length() < 12) count[s.length()].getAndIncrement(); });
		// カウント結果の表示
		System.out.println(Arrays.toString(count));
	}
	
	public static void main(String[] args) {
		String contents = "";
		try {
			contents = new String(Files.readAllBytes(Paths.get("src/ch02/ex02_12/Alice.txt")), 
					StandardCharsets.UTF_8);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<String> words = Arrays.asList(contents.split("\\P{L}+"));
		
		ShortWordsCounter swc = new ShortWordsCounter();
		swc.count(words.stream());

	}

}
