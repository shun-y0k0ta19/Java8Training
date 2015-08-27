package ch02.ex02_09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class ArrayListIntegrator {
	/**
	 * ArrayList<T>のStreamをArrayListにまとめる
	 * @param streamOfArrayList
	 * @throws NullPointerException streamOfArrayListがnullのとき
	 */
	public <T> ArrayList<T> integrate(Stream<ArrayList<T>> streamOfArrayList){
		Objects.requireNonNull(streamOfArrayList, "streamOfArrayList is null.");
		return integrate1(streamOfArrayList);
	}
	
	private <T> ArrayList<T> integrate1(Stream<ArrayList<T>> streamOfArrayList){
		ArrayList<T> integratedList = streamOfArrayList.reduce(new ArrayList<T>(), (r, e) -> { 
			r.addAll(e);
			return r;
		});
		return integratedList;
	}

	private <T> ArrayList<T> integrate2(Stream<ArrayList<T>> streamOfArrayList){
		ArrayList<T> integratedList = streamOfArrayList.reduce((r, e) -> {
			r.addAll(e);
			return r;
		}).orElse(new ArrayList<T>());
		return integratedList;
	}

	private <T> ArrayList<T> integrate3(Stream<ArrayList<T>> streamOfArrayList){
		ArrayList<T> integratedList = streamOfArrayList.reduce(new ArrayList<T>(), 
				(r, e) -> {
					r.addAll(e);
					return r;
				}, (r, s) -> {
					r.addAll(s);
					return r;
				});
		return integratedList;
	}

	public static void main(String[] args){
		String[] names = {"John", "Mike", "Peter", "Paul", "Tom"};
		List<ArrayList<String>> listOfArrayList = new ArrayList<>();
		for(int i = 0 ; i < 5 ; i++){
			listOfArrayList.add(new ArrayList<>(Arrays.asList(names)));
		}
		ArrayListIntegrator ali = new ArrayListIntegrator();
		System.out.println("Integrate1");
		ali.integrate1(listOfArrayList.stream()).forEach(System.out::println);
		System.out.println("Integrate2");
		ali.integrate2(listOfArrayList.stream()).forEach(System.out::println);
		System.out.println("Integrate3");
		ali.integrate3(listOfArrayList.stream()).forEach(System.out::println);
	}
}
