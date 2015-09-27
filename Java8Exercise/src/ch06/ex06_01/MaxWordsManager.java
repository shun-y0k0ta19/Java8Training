package ch06.ex06_01;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class MaxWordsManager {

	public void MaxWordsCount() throws InterruptedException, IOException{
		String[] words;
		String contents = new String(
				Files.readAllBytes(Paths.get("war-and-peace.txt")), 
				StandardCharsets.UTF_8);

		words = contents.split("[\\P{L}]+");
		final int COUNT = 5000;
		int nTask = (int)Math.ceil(words.length / (double)COUNT);

		AtomicReference<String> longestWord = new AtomicReference<String>("");
		ExecutorService pool = Executors.newCachedThreadPool();
		for (int n = 0; n < nTask; n++) {
			int start = COUNT * n;
			int end   = Math.min(start + COUNT, words.length);
			pool.submit(
					() -> {
						for (int i = start; i < end; i++) {
							String word = words[i];
							longestWord.updateAndGet(
									w -> word.length() > w.length() ? word: w
									);
						}
					}
					);
		}
		pool.shutdown();
		pool.awaitTermination(10, TimeUnit.SECONDS);
		System.out.println("最長の単語 : " + longestWord);
	}
}
