package ch07.ex08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class NextYearJS {

	public static void main(String[] args) {
		String pathString = "src/ch07/ex08/nextYear.js";
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("nashorn");
		try {
			engine.eval(Files.newBufferedReader(Paths.get(pathString)));
		} catch (ScriptException | IOException e) {
			e.printStackTrace();
		}
	}

}
