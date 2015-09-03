package ch02.ex02_02;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


public class Limit5Logger {
	
	public void LongWordsCount(String fileName) throws IOException{
		String contents = new String(Files.readAllBytes(Paths.get(fileName)), 
				StandardCharsets.UTF_8);
		List<String> words = Arrays.asList(contents.split("\\P{L}+"));
		words.stream()
	    .filter(w -> { System.out.println("word is " + w); return w.length() > 6; })
	    .limit(5)
	    .forEach(System.out::println);

	}
	
	public static void main(String[] args) {
		Limit5Logger limit5Logger = new Limit5Logger();
		try {
			limit5Logger.LongWordsCount("src/ch02/ex02_02/Alice.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
