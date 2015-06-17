/*
 * 回答
 * sortを呼び出したスレッドで実行される
 */

package ch01.ex01_01;

import java.util.Arrays;
import java.util.Random;

public class ShowSortingThreadID {
	private final int length = 10;
	private Random rand = new Random();
	
	public void showSortingThread(){
		String[] strings = new String[length];
		for(int i = 0; i < strings.length; i++){
			strings[i] = Integer.toString(rand.nextInt(length));
		}
		
		System.out.println("ID of a Thread calling <Arrays.sort> is " + Thread.currentThread().getId());
		Arrays.sort(strings, (str1, str2) -> {
			System.out.println("This ThreadID is " + Thread.currentThread().getId());
			return str1.compareTo(str2);
		});
	}
	
	public static void main(String[] args) {
		System.out.println("MainThread ID is " + Thread.currentThread().getId());
		ShowSortingThreadID sst = new ShowSortingThreadID();
		new Thread(sst::showSortingThread).start();
	}

}
