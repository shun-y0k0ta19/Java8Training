package ch01.ex01_02;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestshowSubDirectories {
	private FileExplorer fileExplorer = new FileExplorer();
	
	@Rule
	private ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testNullArgument() {
		exception.expect(NullPointerException.class);
		fileExplorer.getSubDirectories(null);
	}

	@Test
	public void testPathIsFile() {
		
	}
	
}
