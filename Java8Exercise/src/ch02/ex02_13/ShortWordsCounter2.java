package ch02.ex02_13;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShortWordsCounter2 {

	public void count(Stream<String> wordsStream){
		Map<Integer, Long> wordsMap = wordsStream.parallel()
				.filter(s -> s.length() < 12)
				.collect(Collectors.groupingBy(String::length, Collectors.counting()));
		// カウント結果の表示
		for (int n = 0; n < 12; n++) {
			System.out.print(wordsMap.get(n));
			System.out.print(", ");
		}
	}

	public static void main(String[] args) {
		String contents = "";
		try {
			contents = new String(Files.readAllBytes(Paths.get("src/ch02/ex02_13/Alice.txt")), 
					StandardCharsets.UTF_8);

		} catch (IOException e) {
			e.printStackTrace();
		}
		List<String> words = Arrays.asList(contents.split("\\P{L}+"));

		ShortWordsCounter2 swc2 = new ShortWordsCounter2();
		swc2.count(words.stream());

	}

}
