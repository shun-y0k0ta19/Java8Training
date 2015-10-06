package ex05;

import static MyUtilities.FileUtils.createWordSet;
import static org.junit.Assert.*;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeoutException;

import org.junit.Before;
import org.junit.Test;

import ch06.ex05.WordCollector;

public class WordCollectorTest {
	private WordCollector wc;
	private HashSet<File> files;
	private ConcurrentHashMap<String, Set<File>> correctConcurrentHashMap;


	@Before
	public void setUp(){
		wc = new WordCollector();
		correctConcurrentHashMap = new ConcurrentHashMap<>();
		files = new HashSet<>();
		files.add(new File("test/ex05/text1"));
		files.add(new File("test/ex05/text2"));		
		createCorrectHashMap(correctConcurrentHashMap, files);
	}

	@Test
	public void collectFilesSetAndWords() {
		ConcurrentHashMap<String, Set<File>> resultOfcollectWords;
		resultOfcollectWords = wc.collectWords(files);
		assertEquals("collectWords does not return correct Map.", 
				correctConcurrentHashMap, 
				resultOfcollectWords);
	}

	@Test
	public void collectFilesSetAndWordsWithExecutorService() {
		ConcurrentHashMap<String, Set<File>> resultOfcollectWordsWithExecutorService;

		try {
			resultOfcollectWordsWithExecutorService = wc.collectWordsWithExecutorService(files);
		} catch (InterruptedException | TimeoutException e) {
			e.printStackTrace();
			throw new RuntimeException("collectWordsWithExecutorService throws exeption!", e);
		}
		assertEquals("collectWrodsWithExecutorService does not return correct Map.", correctConcurrentHashMap, resultOfcollectWordsWithExecutorService);



	}

	private void createCorrectHashMap(ConcurrentHashMap<String, Set<File>> correctFilesContainingWord,
			HashSet<File> files) {
		for(File file : files){
			Set<String> wordSet = createWordSet(file.toPath());
			for(String word : wordSet){
				Set<File> fileSet = new HashSet<>();
				fileSet.add(file);
				correctFilesContainingWord.merge(word, fileSet, (existSet, newSet) -> {
					existSet.addAll(newSet);
					return existSet;
				});
			}
		}
	}

}
