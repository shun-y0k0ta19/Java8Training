package ch03.ex03_20;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListMapper {
	
	/**
	 * 指定された関数をTのリストに適用した結果から構成されるUを格納したリストを返します
	 * @param list mapperを適用する要素を持つリスト
	 * @param mapper 各要素に適用する非干渉でステートレスな関数
	 * @return mapperを適用した要素を持つリスト
	 */
	public static <T, U> List<U> map(List<T> list, Function<T, U> mapper){
		return list.stream().map(mapper).collect(Collectors.toList());
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
