package ch05.ex05_12;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Plan {
	private String subject;
	private ZonedDateTime time;
	
	public Plan(String subject, ZonedDateTime time){
		this.subject = subject;
		this.time = time;
	}
	
	public String getSubject(){
		return subject;
	}
	
	public ZonedDateTime getTime(){
		return time;
	}
	
	public String toString(){
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
		return "subject: " + subject + " time: " + formatter.format(time);
	}
}
