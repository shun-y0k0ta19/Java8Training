package ch06.ex06_06;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class WordReader implements AutoCloseable {
    /** テキストファイル読み込み */
    private final BufferedReader reader;
    /** 1行分の単語 */
    private List<String> words = Collections.emptyList();

    /** コンストラクタ(文字コード UTF-8) */
    public WordReader(File file) throws IOException {
        this(file, StandardCharsets.UTF_8);
    }
    /** コンストラクタ */
    public WordReader(File file, Charset charset) throws IOException {
        reader = new BufferedReader(
            new InputStreamReader(new FileInputStream(file), charset)
        );
    }
    /** テキストファイルを閉じる */
    public void close() throws IOException {
        reader.close();
    }
    /**
     * 単語の読み込み
     * ファイルの終わりでは null を返す
     */
    public String getWord() throws IOException {
        while (words.isEmpty()) {
            String line = reader.readLine();
            if (line == null) {
                return null;
            }
            words = new LinkedList<String>(Arrays.asList(line.split("[\\P{L}]+")));
                // Arrays.asList の戻り値は remove をサポートしていないため
        }
        return words.remove(0);
    }

}
