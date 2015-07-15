package ch01.ex01_04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class FileExplorer {
	private final File[] EMPTY_FILES = new File[0];
	private final String[] EMPTY_FILENAMES = new String[0];
	/**
	 * directoryで指定されたディレクトリ内のサブディレクトリ情報を含むFileクラスの配列を返します。
	 * ディレクトリでないパスを指定した場合、指定したディレクトリが存在しない場合、サブディレクトリが存在しない場合は、空配列を返します。
	 * @param directory サブディレクトリを表示させたいディレクトリ情報を示すFileクラスのインスタンス
	 * @throws NullPointerException directory引数がnullである場合
	 */
	public File[] getSubDirectories(File directory) {		//File currentDirectory = new File(path);
		if(Objects.isNull(directory)){
			throw new NullPointerException();
		}
		if(!directory.exists()){
			System.out.println("No such file or Directory.");
			return EMPTY_FILES;
		}
		if(directory.isFile()){
			System.out.println("Target \"" + directory.getAbsolutePath() + "\" is not a directory.");
			return EMPTY_FILES;
		}

		File[] subDirectories = directory.listFiles(dir -> dir.isDirectory());
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
		String effectivelyFinalExtension;
		if(Objects.isNull(directory)){
			throw new NullPointerException();
		}
		if(Objects.isNull(extension)){
			effectivelyFinalExtension = "";
		}
		else{
			effectivelyFinalExtension = '.' + extension;
		}
		if(!directory.exists()){
			System.out.println("No such file or Directory.");
			return EMPTY_FILENAMES;
		}
		if(directory.isFile()){
			System.out.println("Target \"" + directory.getAbsolutePath() + "\" is not a directory.");
			return EMPTY_FILENAMES;
		}
		String[] fileNames = directory.list((dir, name) -> name.endsWith(effectivelyFinalExtension));

		return fileNames;
	}

	/**
	 * Fileクラスの配列を受け取りソートした後、その配列を返します。
	 * ディレクトリを前方に、ファイルを後方にまとめ、それぞれのグループはパス名で昇順にソートされます。
	 * @param files ソートを行うFileクラスの配列
	 * @return ソート後のFileクラスの配列
	 * @throws 引数filesがnullの場合
	 */
	public File[] sortFiles(File[] files){
		if(Objects.isNull(files)){
			throw new NullPointerException();
		}
		Arrays.sort(files, (File first, File second) -> {
			if(first.isDirectory()){
				if(second.isFile()){
					return -1;
				}
				return first.getAbsolutePath().compareTo(second.getAbsolutePath());
			}
			if(second.isDirectory()){
				return 1;
			}
			return first.getAbsolutePath().compareTo(second.getAbsolutePath());
		});
		return files;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.printf("input directory path.%n> ");
		String path = reader.readLine();
		FileExplorer fileExplorer = new FileExplorer();
		path = "/Users/design/Documents/WebServer";
		File[] files = new File(path).listFiles();
		fileExplorer.sortFiles(files);
		for(File file : files){
			System.out.println(file.getAbsolutePath());
		}
	}


}
