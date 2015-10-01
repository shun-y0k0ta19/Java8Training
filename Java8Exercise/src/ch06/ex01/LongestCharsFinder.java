package ch06.ex01;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

import static Utilities.FileUtils.*;

public class LongestCharsFinder {

	/**
	 * 指定されたファイル中で最も長い文字列を持つ単語を検索し、返します。
	 * @param textFilePath テキストファイルへのパス
	 * @return ファイル中で最大の長さを持つ文字列
	 * @throws IOException ストリームからの読取り中に入出力エラーが発生した場合
	 * @throws InterruptedException 待機中に割込みが発生した場合
	 * @throws TimeoutException 処理がタイムアウトした場合(10秒)
	 */
	public String findMaxCharsWord(String textFilePath) throws IOException, InterruptedException, TimeoutException{
		final int WORDS_NUM_ASSINED_THREAD = 50000;
		AtomicReference<String> longestWord = new AtomicReference<>("");

		List<String> words = createWordList(textFilePath);
		
		ExecutorService es = Executors.newCachedThreadPool();
		for(int start = 0; start < words.size(); start += WORDS_NUM_ASSINED_THREAD){
			int end = start + WORDS_NUM_ASSINED_THREAD;
			if(end > words.size()){
				end= words.size();
			}
			List<String> assinedWords = words.subList(start, end);
			es.submit(() ->{
				for(String nw : assinedWords){
					longestWord.updateAndGet(ow -> ow.length() > nw.length() ? ow : nw);
				}
			});
		}
		es.shutdown();
		if(es.awaitTermination(10, TimeUnit.SECONDS)){
			return longestWord.get();
		}
		throw new TimeoutException("Finding longest word is time out!");
	}

	public static void main(String[] args){
		LongestCharsFinder lcf = new LongestCharsFinder();
		try {
			String longestWord = lcf.findMaxCharsWord("src/ch06/ex01/war-and-peace.txt");
			System.out.println(longestWord);
		} catch (IOException | InterruptedException | TimeoutException e) {
			e.printStackTrace();
		}
	}

}
