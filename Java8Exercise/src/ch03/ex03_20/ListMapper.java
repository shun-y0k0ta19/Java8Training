package ch03.ex03_20;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListMapper {
	public static <T, U> List<U> map(List<T> list, Function<T, U> f){
		return list.stream().map(f).collect(Collectors.toList());
	}
	
	public static void main(String[] args){
		List<Person> pList = new ArrayList<Person>();
		pList.add(new Person("John" , 20));
		pList.add(new Person("Mark", 22));
		pList.add(new Person("Peter", 27));
		
		List<CopyOfPerson> copList = map(pList, (t) -> new CopyOfPerson(t));
		
		copList.stream().forEach(System.out::println);
	}
}
