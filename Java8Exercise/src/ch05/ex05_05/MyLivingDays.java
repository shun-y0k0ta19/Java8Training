package ch05.ex05_05;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class MyLivingDays {

	public static void main(String[] args) {
		LocalDate myBirthDay = LocalDate.of(1988, 9, 5);
		LocalDate today = LocalDate.now();
		long myLivingDays = myBirthDay.until(today, ChronoUnit.DAYS);
		System.out.println(myLivingDays);
		System.out.println(LocalDate.of(1988, 10, 12).plusDays(10000));
	}

}
