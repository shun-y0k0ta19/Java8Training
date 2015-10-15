package MyUtilities;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyStringUtils {
	/**
	 * 文字列から抽出した単語を要素に持つリストを返す
	 * @param contents スペースで区切りたい文字列
	 * @return 単語のリスト
	 */
	public static List<String> splitBySpace(String contents) {
		return Stream.of(contents.split("[^\\S]+")).filter(w -> !w.equals("")).collect(Collectors.toList());
	}

}
