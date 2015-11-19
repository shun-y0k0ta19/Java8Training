package ch09.ex05;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReverseWR {
	public static void main(String[] args) {

        try {
            byte[] source = Files.readAllBytes(Paths.get(new File("src/ch09/ex05/ReverseWR.java").toURI()));
            byte[] reversed = new byte[source.length];
            for (int i = source.length - 1; i >= 0; i--) {
                reversed[source.length - 1 - i] = source[i];
            }
            Files.write(Paths.get(new File("src/ch09/ex05/res.txt").toURI()), reversed);
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
}
