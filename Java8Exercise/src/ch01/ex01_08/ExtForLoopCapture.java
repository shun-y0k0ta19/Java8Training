package ch01.ex01_08;

import java.util.ArrayList;
import java.util.List;

public class ExtForLoopCapture {
	
	/*
	回答
	正当である。
	*/
	public static void main(String[] args){
		String[] names = { "Peter", "Paul", "Mary" };
		List<Runnable> runners = new ArrayList<>();
		for(String name : names){
			runners.add(() -> System.out.println(name));
		}
		for(Runnable runner : runners){
			new Thread(runner).start();
		}
	}
	
	public static void notExtForLoop(){
		String[] names = { "Peter", "Paul", "Mary" };
		List<Runnable> runners = new ArrayList<>();
		for(int i = 0; i < names.length; i++){
			//runners.add(() -> System.out.println(names[i]));
			//拡張for文でないとエラーとなる(iがeffectively finalでないため)
			int j = i; //effectively finalな変数を一度経由する必要がある。
			runners.add(() -> System.out.println(names[j]));
		}
		for(Runnable runner : runners){
			new Thread(runner).start();
		}

	}

}

