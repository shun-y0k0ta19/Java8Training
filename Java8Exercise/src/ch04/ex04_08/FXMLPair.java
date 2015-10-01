package ch04.ex04_08;

import ch03.ex03_20.Person;

public class FXMLPair {
	private Person no1;
	private Person no2;
	
	public FXMLPair(Person no1, Person no2){
		setPair(no1, no2);
	}
	
	public void setPair(Person no1, Person no2){
		if(no1 != null)
			this.no1 = no1;
		if(no2 != null)
			this.no2 = no2;
	}
	
	public Person getNo1(){
		return no1;
	}
	
	public Person getNo2(){
		return no2;
	}
	
	
	@Override
	public String toString(){
		return "No.1: " + no1 + " No.2: " + no2;
	}
	
}
