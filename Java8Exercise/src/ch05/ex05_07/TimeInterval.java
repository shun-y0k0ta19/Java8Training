package ch05.ex05_07;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeInterval {
    public final LocalDateTime start;
    public final LocalDateTime end;

    public TimeInterval(LocalDate date, LocalTime start, LocalTime end) {
        this(date.atTime(start), date.atTime(end));
    }

    public TimeInterval(LocalDateTime start, LocalDateTime end) {
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start is after end.");
        }
        this.start = start;
        this.end = end;
    }
    
    public boolean isOverlapped(TimeInterval other) {
        if(start.isAfter(other.start) && start.isBefore(other.end)){
        	return true;
        }
        if(end.isAfter(other.start)){
        	return true;
        }
        
        return false;
    }
    
	public static void main(String[] args) {
		TimeInterval ti1 = new TimeInterval(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
		TimeInterval ti2 = new TimeInterval(LocalDateTime.now().plusHours(1).minusMinutes(5), LocalDateTime.now().plusHours(1).minusMinutes(3));
		System.out.println(ti1.isOverlapped(ti2));
	}

}
