package ch08.ex08;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class CheckedQueueTester {
	@SuppressWarnings({ "unchecked", "rawtypes" })
    public static void main(String[] args) {
		Queue unchecked = new LinkedList<>();
        unchecked.add(Paths.get("path1"));
        System.out.println("unchecked=" + unchecked);
        unchecked.add("string1");
        System.out.println("unchecked=" + unchecked);
        Queue checked = Collections.checkedQueue(unchecked, Path.class);
        checked.add(Paths.get("path2"));
        System.out.println("  checked=" + checked);
        try {
            checked.add("string2");
        } catch (ClassCastException e) {
            System.err.println("CCE caught: " + e.getMessage());
            System.out.println("  checked=" + checked);
        }
    }
}
