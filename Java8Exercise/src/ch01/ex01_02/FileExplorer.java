package ch01.ex01_02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Objects;
import java.util.stream.Stream;

public class FileExplorer {
	private final File[] EMPTY_FILES = new File[0];
	private final String[] EMPTY_FILENAMES = new String[0];

	/**
	 * directoryで指定されたディレクトリ内のサブディレクトリ情報を抽出します。
	 * @param directory サブディレクトリを表示させたいディレクトリ情報を示すFileクラスのインスタンス
	 * @return directoryで指定されたディレクトリ内のサブディレクトリ情報を含むFileクラスの配列を返します。<br>
	 * ディレクトリでないパスを指定した場合、指定したディレクトリが存在しない場合、サブディレクトリが存在しない場合は、空配列を返します。
	 * @throws NullPointerException directory引数がnullである場合
	 */
	public File[] getSubDirectories(File directory) {		//File currentDirectory = new File(path);
		Objects.requireNonNull(directory);
		if(!directory.exists()){
			return EMPTY_FILES;
		}
		if(directory.isFile()){
			return EMPTY_FILES;
		}

		File[] subDirectories = directory.listFiles(dir -> dir.isDirectory());
		if(subDirectories == null){
			return EMPTY_FILES;
		}
		for(File subDir : subDirectories){
			System.out.println(subDir.getName());
		}
		return subDirectories;
	}

	/**
	 * 指定したディレクトリにあるファイルのうち指定した拡張子と一致するファイル名を含むStringの配列を返します。<br>
	 * ディレクトリでないパスを指定した場合、指定したディレクトリが存在しない場合、拡張子と一致するファイル名が存在しない場合は、空配列を返します。
	 * extensionに空文字列を渡した場合も空配列を返します。
	 * @param directory
	 * @param extension
	 * @throws NullPointerException directory引数がnullである場合
	 */
	public String[] getFileNames(File directory, String extension){
		Objects.requireNonNull(directory);
		
		String effectivelyFinalExtension;
		if(Objects.isNull(extension)){
			effectivelyFinalExtension = "";
		}
		else{
			effectivelyFinalExtension = '.' + extension;
		}
		if(!directory.exists()){
			return EMPTY_FILENAMES;
		}
		if(directory.isFile()){
			return EMPTY_FILENAMES;
		}
		String[] fileNames = directory.list((dir, name) -> name.endsWith(effectivelyFinalExtension));
		
		return fileNames;
	}
	
	/**
	 * directoryで指定されたディレクトリ内のサブディレクトリ情報を含むFileクラスの配列を返します。
	 * showSubDirectoriesメソッドとの違いは関数インターフェースの使用の有無です。
	 * ディレクトリでないパスを指定した場合、指定したディレクトリが存在しない場合、サブディレクトリが存在しない場合は、空配列を返します。
	 * @param directory サブディレクトリを表示させたいディレクトリ情報を示すFileクラスのインスタンス
	 * @throws NullPointerException directory引数がnullである場合
	 */
	public File[] getSubDirectoriesUsingFunctionalInterface(File directory){
		if(Objects.isNull(directory)){
			throw new NullPointerException();
		}
		if(directory.isFile()){
			//System.out.println("Target \"" + directory.getAbsolutePath() + "\" is not a directory.");
			return EMPTY_FILES;
		}
		if(!directory.exists()){
			//System.out.println("No such file or Directory.");
			return EMPTY_FILES;
		}

		FileFilter directoryFilter = file -> file.isDirectory();
		File[] subDirectories = directory.listFiles(directoryFilter);
		if(subDirectories == null){
			return EMPTY_FILES;
		}
		for(File file : subDirectories){
			System.out.println(file.getName());
		}
		return subDirectories;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.printf("input directory path.%n> ");
		String path = reader.readLine();
		if(path == null){
			path = "";
		}
		FileExplorer fileExplorer = new FileExplorer();
		System.out.println("SubDirectories:");
		Stream.of(fileExplorer.getSubDirectories(new File(path))).forEach(System.out::println);
		System.out.println("SubDirectories:");
		fileExplorer.getSubDirectoriesUsingFunctionalInterface(new File(path));
		//fileExplorer.showSubDiretories("/Users/design/Documents/WebServer/index.js");
		//fileExplorer.showSubDiretories("/daf");
		//fileExplorer.showSubDiretories("/Users/design/Documents/test");
		//fileExplorer.showSubDiretories(new File("/Users/design/Documents/WebServer/index.js"));
		//fileExplorer.showSubDiretories(null);
	}

}
