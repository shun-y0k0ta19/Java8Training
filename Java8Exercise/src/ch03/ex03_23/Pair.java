package ch03.ex03_23;

import java.util.function.Function;

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
	
	public <U> Pair<U> map(Function<T, U> f) {
        return new Pair<U>(f.apply(no1), f.apply(no2));
    }
	
	@Override
	public String toString(){
		return "No.1: " + no1 + " No.2: " + no2;
	}
	
	public static void main(String[] args){
		Person p1 = new Person("John", 20);
		Person p2 = new Person("Mark", 22);
		Pair<Person> pair = new Pair<>(p1, p2);
		Pair<Integer> pairAge = pair.map(p -> p.getAge());
		System.out.println(pairAge.toString());
	}
}
