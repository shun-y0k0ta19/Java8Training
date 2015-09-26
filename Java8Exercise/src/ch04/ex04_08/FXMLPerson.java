package ch04.ex04_08;

public class FXMLPerson {
	private String name;
	private int age;
	
	public FXMLPerson(String name, int age){
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
