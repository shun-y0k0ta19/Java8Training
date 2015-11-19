package ch08.ex15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Grep {
	public static void main(String[] args) throws IOException {
        Predicate<String> predicate = Pattern.compile("^import").asPredicate();
        Files.lines(Paths.get("/Users/design/git/Java8Training/Java8Exercise/src/ch08/ex15/Grep.java")).filter(predicate).forEach(System.out::println);
    }
}
