package ch09.ex06;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class ReverseWRLine {
	public static void main(String[] args) {

        try {
        	List<String> source = Files.readAllLines(Paths.get(new File("src/ch09/ex06/ReverseWRLine.java").toURI()));
            Collections.reverse(source);
        	Files.write(Paths.get(new File("src/ch09/ex06/res.txt").toURI()), source);
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }

}
