package ch08.ex05;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;

public class WordCounter {

	public static void main(String[] args) {
		String contents;
		try {
			contents = new String(Files.readAllBytes(Paths.get("src/ch08/ex05/war-and-peace.txt")), 
					StandardCharsets.UTF_8);
			List<String> words = Arrays.asList(contents.split("\\P{L}+"));

			long begin = System.nanoTime();
			words.stream().filter(w -> w.length() > 12).count();
			long end = System.nanoTime();
			System.out.println(end - begin);
			
			LongAdder countLambda = new LongAdder();
			begin = System.nanoTime();
			words.forEach(w -> {
				if(w.length() > 12) {
					countLambda.increment();
				}
			});
			end =System.nanoTime();
			System.out.println(end - begin);

			
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

}
