package ch02.ex02_11;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class ArrayListCollector {
	
	public <T> ArrayList<T> arrayListParallelCollect(ArrayList<T> arrayList, Stream<T> stream){
		final AtomicInteger index = new AtomicInteger(0);
		stream.parallel().forEach((element) -> {
			arrayList.set(index.getAndIncrement(), element);
		});
		return arrayList;
		
	}

	public static void main(String[] args){
		ArrayListCollector alc = new ArrayListCollector();
		final int listNum = 20;
		ArrayList<String> list = new ArrayList<>();
		for(int i = 0; i < listNum; i++){
			list.add(String.valueOf(i));
		}
		Stream<String> stream = list.stream();
		alc.arrayListParallelCollect(list, stream);
		list.stream().forEach(System.out::println);
	}
}
