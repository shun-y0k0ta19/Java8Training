package ch09.ex02;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class NoTryWithResource {
	public static void main(String[] args) throws Throwable {
		Throwable exOrigin = null;
		Scanner in = null;
		PrintWriter out = null;
		try {
			in = new Scanner(new File("src/ch09/ex01/NoTryWithResource.java"));
			out = new PrintWriter(new File("src/ch09/ex01/ch9.ex01.txt"));
			while (in.hasNext()) {
				out.println(in.next().toLowerCase());
			}
		} catch (Throwable ex) {
			exOrigin = ex;
			throw ex;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Throwable e) {
					exOrigin.addSuppressed(e);
					exOrigin.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (Throwable e) {
					exOrigin.addSuppressed(e);
					exOrigin.printStackTrace();
				}
			}
		}
	}
}
