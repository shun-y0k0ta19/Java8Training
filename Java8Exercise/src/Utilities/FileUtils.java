package Utilities;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileUtils {

	/**
	 * パスで指定されたファイル内のすべての単語を格納したリストを生成する。
	 * @param path ファイルへのパス
	 * @return パスで指定されたファイルの単語のリスト
	 * @throws IOException　ストリームからの読取り中に入出力エラーが発生した場合
	 */
	public static List<String> createWordsList(Path path) throws IOException {
		String contents = new String(Files.readAllBytes(path), 
				StandardCharsets.UTF_8);
		return Arrays.asList(contents.split("\\P{L}+"));
	}
	
	/**
	 * パスで指定されたファイル内のすべての単語を格納したリストを生成する。
	 * @param path パス文字列
	 * @return パスで指定されたファイルの単語のリスト
	 * @throws IOException　ストリームからの読取り中に入出力エラーが発生した場合
	 */
	public static List<String> createWordList(String path) throws IOException {
		String contents = new String(Files.readAllBytes(Paths.get(path)), 
				StandardCharsets.UTF_8);
		return Arrays.asList(contents.split("\\P{L}+"));

	}
	

}
