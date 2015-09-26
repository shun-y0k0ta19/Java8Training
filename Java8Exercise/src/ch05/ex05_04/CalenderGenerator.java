package ch05.ex05_04;

import java.time.LocalDate;
import java.time.YearMonth;

public class CalenderGenerator {
	public static void main(String[] args){
		YearMonth yearMonth = YearMonth.of(Integer.parseInt(args[1]), Integer.parseInt(args[0]));
		LocalDate day = yearMonth.atDay(1);
		int dayOfWeek = day.getDayOfWeek().getValue();
		String LF = dayOfWeek == 7 ? "\n" : "";
		for(int i = 0; i < dayOfWeek; i++){
			System.out.print(dayOfWeek - i != 1 ? "   " : "  1" + LF);
		}
		while(!day.equals(yearMonth.atEndOfMonth())){
			day = day.plusDays(1);
			if(day.getDayOfWeek().getValue() % 7 == 0){
				System.out.printf("%3d\n", day.getDayOfMonth());
			}
			else {
				System.out.printf("%3d", day.getDayOfMonth());
			}
		}
	}
}
