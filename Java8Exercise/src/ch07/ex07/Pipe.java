package ch07.ex07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import MyUtilities.MyStringUtils;

public class Pipe {

	private static void pipe(String... args) {
		List<ProcessBuilder> pbList = new ArrayList<>();
		List<Process> processList = new ArrayList<>();
		for(String arg : args) {
			List<String> commandsAndOptions = MyStringUtils.splitBySpace(arg); 
			pbList.add(new ProcessBuilder(commandsAndOptions));			
		}
		for(int i = 0; i < pbList.size() - 1; i++) {
			System.out.println(pbList.get(i).redirectOutput());
			System.out.println(pbList.get(i).redirectInput());
			
			pbList.get(i).redirectOutput(pbList.get(i+1).redirectInput());
		}
		try {
			for(ProcessBuilder pb : pbList) {
				//pb.in
				processList.add(pb.start());
			}
			for(int i = 0; i < processList.size() - 1; i++) {
				//processList.get(i)
			}
			printInputStream(processList.get(processList.size() - 1).getInputStream());

		} catch (IOException e) {
			throw new RuntimeException("Process canot run." , e);
		}
	}

	public static void main(String[] args) {
		pipe("ls -l", "grep e");
	}

	private static void printInputStream(InputStream in) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		try {
			for (;;) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				System.out.println(line);
			}
		} finally {
			br.close();
		}
	}
}
