package ch05.ex05_01;

import java.time.LocalDate;

public class ProgrammersDayCalculator {

	LocalDate calcProgrammersDay(int year){
		return LocalDate.ofYearDay(year, 256);
		
	}
	
	public static void main(String[] args) {
		ProgrammersDayCalculator pdc = new ProgrammersDayCalculator();
		System.out.println(pdc.calcProgrammersDay(2015).toString());

	}

}
