package ch06.ex05;

import static MyUtilities.FileUtils.*;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class WordCollector {

	public ConcurrentHashMap<String, Set<File>> collectWordsFromFiles(Set<File> files){
		ConcurrentHashMap<String, Set<File>> filesContainingWord = new ConcurrentHashMap<>();
		files.stream().parallel().forEach(file -> {
			//merge用のfileのセットを作る
			Set<File> fileSet = new HashSet<>();
			fileSet.add(file);
			
			Set<String> wordSet = createWordSet(file.toPath());
			//wordSet.stream().parallel().forEach(word ->{
			wordSet.stream().forEach(word -> {
				filesContainingWord.merge(word, fileSet, (existSet, newSet) -> {
					existSet.addAll(newSet);
					return existSet;
				});
			});
		});
		return filesContainingWord;
	}
	
	public static void main(String[] args) {
		WordCollector wc = new WordCollector();
		HashSet<File> files = new HashSet<>();
		files.add(new File("src/files/war-and-peace.txt"));
		files.add(new File("src/files/alice.txt"));
		
		long start = System.nanoTime();
		wc.collectWordsFromFiles(files);
		long end = System.nanoTime();
		System.out.print(end - start);

	}

}
