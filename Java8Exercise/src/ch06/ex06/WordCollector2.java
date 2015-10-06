package ch06.ex06;

import static MyUtilities.FileUtils.*;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class WordCollector2 {

	public ConcurrentHashMap<String, Set<File>> collectWords(Set<File> files){
		ConcurrentHashMap<String, Set<File>> filesContainingWord = new ConcurrentHashMap<>();
		files.stream().parallel().forEach(file -> {
			Set<String> wordSet = createWordSet(file.toPath());
			wordSet.stream().forEach(word -> {
				filesContainingWord.computeIfAbsent(word, key -> {
					return new HashSet<File>();
				}).add(file);
			});
		});
		return filesContainingWord;
	}

	public ConcurrentHashMap<String, Set<File>> collectWordsWithExecutorService(Set<File> files) throws InterruptedException, TimeoutException {
		ConcurrentHashMap<String, Set<File>> filesContainingWord = new ConcurrentHashMap<>();
		ExecutorService es = Executors.newCachedThreadPool();
		files.stream().forEach(file -> {
			es.submit(() -> {
				createWordSet(file.toPath()).stream().forEach(word -> {
					filesContainingWord.computeIfAbsent(word, key -> {
						return new HashSet<File>();
					}).add(file);
				});
			});
		});
		es.shutdown();
		if(es.awaitTermination(10, TimeUnit.SECONDS)){
			return filesContainingWord;
		}
		throw new TimeoutException("countWithAtomicLong() is Time out!");
	}

	public static void main(String[] args) {
		WordCollector2 wc = new WordCollector2();
		HashSet<File> files = new HashSet<>();
		for(int i = 1; i < 10; i++) {
			files.add(new File("src/files/war-and-peace" + i + ".txt"));
		}
		files.add(new File("src/files/alice.txt"));
		files.add(new File("src/files/LongAlice.txt"));

		long start, end;
		start = System.nanoTime();
		wc.collectWords(files);
		end = System.nanoTime();
		System.out.println(end - start);

		start = System.nanoTime();
		try {
			wc.collectWordsWithExecutorService(files);
		} catch (InterruptedException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		end = System.nanoTime();
		System.out.println(end - start);

	}

}
