package ch06.ex06_06;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FilesReader {

	public void readWordsFromFiles() throws InterruptedException{
		ConcurrentHashMap<String, Set<File>> map = new ConcurrentHashMap<>();
		CountDownLatch latch = new CountDownLatch(5/* ちょっとダサいけど… */);
		ExecutorService pool = Executors.newCachedThreadPool();
		for (File file : new File(".").listFiles(f -> f.getName().endsWith(".txt"))) {
		    pool.submit(
		        () -> {
		            try {
		                try (WordReader reader = new WordReader(file)) {
		                    String word;
		                    while ((word = reader.getWord()) != null) {
		                        map.computeIfAbsent(
		                            word,
		                            key -> ConcurrentHashMap.newKeySet()
		                        ).add(file);
		                    }
		                }
		            } catch (IOException ex) {
		                ex.printStackTrace();
		            }
		            latch.countDown();
		        }
		    );
		}
		pool.shutdown();
		latch.await();
	}
	
}
