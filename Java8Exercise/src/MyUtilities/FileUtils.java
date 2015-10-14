package MyUtilities;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
		try {
			String contents = new String(Files.readAllBytes(path), 
					StandardCharsets.UTF_8);
			return splitBySpace(contents);

		} catch (IOException e) {
			throw new IllegalArgumentException(path.toUri().toString() + " cannot open.");
		}
	}

	/**
	 * 文字列をスペースで区切り、それぞれの文字列を要素に持つリストを返す
	 * @param contents スペースで区切りたい文字列
	 * @return スペースで区切られた文字列のリスト
	 */
	public static List<String> splitBySpace(String contents) {
		return Stream.of(contents.split("\\P{L}+")).filter(w -> !w.equals("")).collect(Collectors.toList());
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
