package ch05.ex05_03;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.function.Predicate;

public class Scheduler {

	public static TemporalAdjuster next(Predicate<LocalDate> p){
		return TemporalAdjusters.ofDateAdjuster(w -> {
			LocalDate result = w;
			do {
				result = result.plusDays(1);
			} while (!p.test(result));
			return result;
		});
	}
	
	public static void main(String[] args){
		System.out.println(LocalDate.of(2015,9,25).with(next(w -> w.getDayOfWeek().getValue() < 6)));
	}
	
}
