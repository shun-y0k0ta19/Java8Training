package ch03.ex03_20;

public class Person {
	private String name;
	private int age;
	
	public Person(String name, int age){
		this.name = name;
		this.age = age;
	}
	
	public String toString(){
		return "Person name:" + name + " age:" + age + "\n";
	}
	
	public String getName(){
		return name;
	}
	
	public int getAge(){
		return age;
	}
}
