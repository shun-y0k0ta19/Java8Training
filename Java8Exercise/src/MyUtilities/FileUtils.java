package MyUtilities;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class FileUtils {

	/**
	 * パスで指定されたファイル内のすべての単語を格納したリストを生成する。
	 * @param path ファイルへのパス
	 * @return パスで指定されたファイルの単語のリスト
	 */
	public static List<String> createWordList(Path path) {
		Objects.requireNonNull(path, "path is null.");
		return createWordListImpl(path);
	}
	
	/**
	 * パスで指定されたファイル内のすべての単語を格納したリストを生成する。
	 * @param path パス文字列
	 * @return パスで指定されたファイルの単語のリスト
	 */
	public static List<String> createWordList(String path) {
		Objects.requireNonNull(path, "path is null.");
		return createWordListImpl(Paths.get(path));
	}
	
	/**
	 * パスで指定されたファイル内のすべての単語を格納したセットを生成する。
	 * @param path パス文字列
	 * @return パスで指定されたファイルの単語のセット
	 */
	public static Set<String> createWordSet(String path) {
		Objects.requireNonNull(path, "path is null.");
		return createWordSetImpl(Paths.get(path));
	}
	
	/**
	 * パスで指定されたファイル内のすべての単語を格納したセットを生成する。
	 * @param path パス文字列
	 * @return パスで指定されたファイルの単語のセット
	 */
	public static Set<String> createWordSet(Path path) {
		Objects.requireNonNull(path, "path is null.");
		return createWordSetImpl(path);
	}

	
	/**
	 * Pathクラスのオブジェクトを受け取って、そのパスのファイルに含まれる単語のリストを返す実装
	 * @param path ファイルのパス
	 * @return パスで指定されたファイルの単語のリスト
	 */
	private static List<String> createWordListImpl(Path path) {
		String contents;
		try {
			contents = new String(Files.readAllBytes(path), 
					StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new IllegalArgumentException(path.toUri().toString() + " cannot open.");
		}
		return Arrays.asList(contents.split("\\P{L}+"));
	}

	/**
	 * Pathクラスのオブジェクトを受け取って、そのパスのファイルに含まれる単語のセットを返す実装
	 * @param path ファイルのパス
	 * @return パスで指定されたファイルの単語のセット
	 */
	private static Set<String> createWordSetImpl(Path path) {
		Set<String> wordSet = new HashSet<>();
		wordSet.addAll(createWordList(path));
		return wordSet;
	}
	
}
