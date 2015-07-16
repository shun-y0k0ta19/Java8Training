package ch02.ex02_09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class ArrayListIntegrator {
	public <T> void Integrate1(Stream<ArrayList<T>> StreamOfArrayList){
		ArrayList<T> integratedList = StreamOfArrayList.reduce(new ArrayList<T>(), (r, e) -> { 
			r.addAll(e);
			return r;
		});
		integratedList.forEach(System.out::println);
	}

	public <T> void Integrate2(Stream<ArrayList<T>> StreamOfArrayList){
		ArrayList<T> integratedList = StreamOfArrayList.reduce((r, e) -> {
			r.addAll(e);
			return r;
		}).orElse(new ArrayList<T>());
		integratedList.forEach(System.out::println);
	}

	public <T> void Integrate3(Stream<ArrayList<T>> StreamOfArrayList){
		ArrayList<T> integratedList = StreamOfArrayList.reduce(new ArrayList<T>(), 
				(r, e) -> {
					r.addAll(e);
					return r;
				}, (r, s) -> {
					r.addAll(s);
					return r;
				});
		integratedList.forEach(System.out::println);
	}

	public static void main(String[] args){
		String[] names = {"John", "Mike", "Peter", "Paul"};
		List<ArrayList<String>> listOfArrayList = new ArrayList<>();
		for(int i = 0 ; i < 5 ; i++){
			listOfArrayList.add(new ArrayList<>(Arrays.asList(names)));
		}
		ArrayListIntegrator ali = new ArrayListIntegrator();
		System.out.println("Integrate1");
		ali.Integrate1(listOfArrayList.stream());
		System.out.println("Integrate2");
		ali.Integrate2(listOfArrayList.stream());
		System.out.println("Integrate3");
		ali.Integrate3(listOfArrayList.stream());
	}
}
