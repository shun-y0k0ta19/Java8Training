package ch02.ex02_04;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ArrayStreamConverter {

	public void checkStreamofValue(){
		int[] values = {1, 4, 9, 16};
		System.out.println(Stream.of(values).count());
		//結果は1。よってintのstreamは作られない
		//Stream<int[]>のストリームが作られる
		
		IntStream.of(values);
		//上でintのStreamが作られる
	}
	
	public static void main(String[] args){
		ArrayStreamConverter asc = new ArrayStreamConverter();
		asc.checkStreamofValue();
	}
}
