package ch03.ex03_20;

public class CopyOfPerson {
	public String name;
	public int age;
	
	public CopyOfPerson(Person p){
		this.name = p.name;
		this.age = p.age;
	}
	
	public CopyOfPerson(String name, int age){
		this.name = name;
		this.age = age;
	}
	
	public String toString(){
		return "CopyOfPerson name:" + name + " age:" + age + "\n";
	}
}
