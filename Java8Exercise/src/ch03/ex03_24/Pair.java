package ch03.ex03_24;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import ch03.ex03_20.Person;

public class Pair<T> {
	private T no1;
	private T no2;
	
	public Pair(T no1, T no2){
		setPair(no1, no2);
	}
	
	public void setPair(T no1, T no2){
		if(no1 != null)
			this.no1 = no1;
		if(no2 != null)
			this.no2 = no2;
	}
	
	public T getNo1(){
		return no1;
	}
	
	public T getNo2(){
		return no2;
	}
	
	public <U> Pair<U> map(Function<? super T, ? extends U> f) {
        return new Pair<U>(f.apply(no1), f.apply(no2));
    }
	
	public <R> Stream<R> flatMap(Function<? super T,? extends Stream<? extends R>> mapper){
		return Stream.concat(mapper.apply(no1), mapper.apply(no2));
		
	}
	
	@Override
	public String toString(){
		return "No.1: " + no1 + " No.2: " + no2;
	}
	
	public static void main(String[] args){
		Person p1 = new Person("John", 20);
		Person p2 = new Person("Mark", 22);
		Person p3 = new Person("Ben", 21);
		Person p4 = new Person("Steve", 28);
		List<Person> l1 = new ArrayList<>();
		l1.add(p1);
		l1.add(p2);
		List<Person> l2 = new ArrayList<>();
		l2.add(p3);
		l2.add(p4);
		Pair<List<Person>> pair = new Pair<>(l1, l2);
		pair.flatMap(l -> l.stream()).forEach(System.out::println);
	}
}
