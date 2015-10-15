package MyUtilities;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class StringUtilsTest {

	@Test
	public void testSplitBySpace() {
		String testStr1 = " aa bb ";
		String testStr2 = "aa  bb";
		String testStr3 = "aa	bb";
		String te = "-l --l a-l f-p";
		List<String> correct = new ArrayList<>();
		correct.add("aa");
		correct.add("bb");
		MyUtilities.MyStringUtils.splitBySpace(te).forEach(System.out::println);
		assertTrue("cannot split correctly.", correct.equals(MyUtilities.MyStringUtils.splitBySpace(testStr1)));
		assertTrue("cannot split correctly.", correct.equals(MyUtilities.MyStringUtils.splitBySpace(testStr2)));
		assertTrue("cannot split correctly.", correct.equals(MyUtilities.MyStringUtils.splitBySpace(testStr3)));
	}

}
