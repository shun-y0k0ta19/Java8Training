package ch08.ex10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class ZipOpener {
	public static void main(String[] args) throws IOException {
		System.out.print("Path of src.zip: ");
		Scanner scanner = new Scanner(System.in);
		String path = scanner.nextLine();
		show(Paths.get(path)).forEach(System.out::println);
		scanner.close();
	}

	private static Stream<Path> show(Path path) throws IOException {
		return Files.walk(path).filter(p -> p.toFile().isFile())
				.filter(p -> {
					try {
						return Files.readAllLines(p).stream().anyMatch(
								s -> s.contains("transient") || s.contains("volatile"));
					} catch (IOException e) {
						throw new RuntimeException("IOException", e);
					}
				});
	}
}
