package ch09.ex07;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class WebPageCopy {
	public static void main(String[] args) throws IOException {
        String target = "http://google.co.jp";
        URL url = new URL(target);
        Path path = Paths.get("src/ch09/ex07/res.txt");
        try (InputStream input = url.openStream()) {
            Files.copy(input, path, StandardCopyOption.REPLACE_EXISTING);
        }
    }

}
