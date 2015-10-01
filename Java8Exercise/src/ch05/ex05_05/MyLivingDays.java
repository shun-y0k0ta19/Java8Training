package ch05.ex05_05;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class MyLivingDays {

	public static void main(String[] args) {
		myLivingDays(1988, 9, 5);
		calcLivingDays(LocalDate.of(1988, 9, 5), 10000);
	}

	public static void myLivingDays(int year, int month, int day) {
		LocalDate myBirthDay = LocalDate.of(year, month, day);
		LocalDate today = LocalDate.now();
		long myLivingDays = myBirthDay.until(today, ChronoUnit.DAYS);
		System.out.println(myLivingDays);
		System.out.println(LocalDate.of(1988, 10, 12).plusDays(10000));
	}
	
	public static void calcLivingDays(LocalDate birthday, long calcLivingDays){
		System.out.println(birthday.plusDays(calcLivingDays));
	}
	
}
