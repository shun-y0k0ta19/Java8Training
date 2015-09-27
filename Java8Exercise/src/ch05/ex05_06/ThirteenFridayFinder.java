package ch05.ex05_06;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class ThirteenFridayFinder {

	public static void main(String[] args) {
		LocalDate day = LocalDate.of(1901, 1, 13);
		while(day.getYear() < 2001){
			if(day.getDayOfWeek().equals(DayOfWeek.FRIDAY)){
				System.out.println(day.toString());
			}
			day = day.plusMonths(1);
		}
	}

}
