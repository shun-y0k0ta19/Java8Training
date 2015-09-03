package ch02.ex02_01;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParallelLongWordCount {
	private final int SPLIT_NUM = 14;
	private int count = 0;

	public int parallelLongWordCount(String fileName) throws IOException{
		String contents = new String(Files.readAllBytes(Paths.get(fileName)), 
				StandardCharsets.UTF_8);
		List<String> words = Arrays.asList(contents.split("\\P{L}+"));
		List<Thread> thList = new ArrayList<>();
		List<String> devideWords = new ArrayList<>();
		for(int i = 0; i < words.size(); i++){
			devideWords.add(words.get(i));
			if((i+1) % SPLIT_NUM == 0 || i == words.size()-1){
				List<String> captureWords = devideWords;
				Thread th = new Thread(new Runnable(){ 
					public void run() {
						for(String w : captureWords){
							if (w.length() > 6){
								count();
							}
						}
					}
				});
				thList.add(th);
				th.start();
				devideWords = new ArrayList<>();;
			}
		}
		boolean threadAllDone = false;
		while(!threadAllDone){
			threadAllDone = true;
			for(Thread th : thList){
				threadAllDone = !th.isAlive();
			}
		}
		return count;
	}

	synchronized private void count(){
		count++;
	}

	public static void main(String[] args) {
		ParallelLongWordCount plwc = new ParallelLongWordCount();
		try {
			System.out.println(plwc.parallelLongWordCount("src/ch02/ex02_01/Alice.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
