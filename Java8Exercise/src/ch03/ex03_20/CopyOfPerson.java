package ch03.ex03_20;

public class CopyOfPerson {
	private String name;
	private int age;
	
	public CopyOfPerson(Person p){
		this.name = p.getName();
		this.age = p.getAge();
	}
	
	public CopyOfPerson(String name, int age){
		this.name = name;
		this.age = age;
	}
	
	public String toString(){
		return "CopyOfPerson name:" + name + " age:" + age + "\n";
	}
}
