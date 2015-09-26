package ch05.ex05_02;

import java.time.LocalDate;

public class YearAdder {

	public static void main(String[] args) {
		LocalDate leapYearDay = LocalDate.of(2000, 2, 29);
		System.out.println(leapYearDay.plusYears(1));
		System.out.println(leapYearDay.plusYears(4));
		LocalDate fourYearAddDay = leapYearDay;
		for(int i = 0; i < 4; i++){
			fourYearAddDay = fourYearAddDay.plusYears(1);
		}
		System.out.println(fourYearAddDay);
		

	}

}
