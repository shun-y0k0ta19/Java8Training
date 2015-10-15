package MyUtilities;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class FileUtilsTest {
	private final String textPathString = "test/FilesForTests/MyUtilities/war-and-peace.txt";
	private final Path textPath = Paths.get(textPathString);
	
	@Test
	public void testCreateCorrectWordListFromPath() {
		List<String> correctWordList = createCorrectWordList();
		List<String> observedWordList = MyUtilities.MyFileUtils.createWordList(textPath);
		assertEquals("createWordList() cannot make correct wordlist!", correctWordList, observedWordList);
	}

	@Test
	public void testCreateCorrectWordListFromString() {
		List<String> correctWordList = createCorrectWordList();
		List<String> observedWordList = MyUtilities.MyFileUtils.createWordList(textPathString);
		assertEquals("createWordList() cannot make correct wordlist!", correctWordList, observedWordList);
	}
	
	private List<String> createCorrectWordList() {
		String contents;
		try {
			contents = new String(Files.readAllBytes(textPath),
					StandardCharsets.UTF_8);
		} catch (IOException e) {
			// unreachable
			throw new RuntimeException("Testing file cannot open. (Unreachble! Check testcode or testing file.)");
		}
		return Arrays.asList(contents.split("[\\P{L}]+"));
	}
	
	

}
