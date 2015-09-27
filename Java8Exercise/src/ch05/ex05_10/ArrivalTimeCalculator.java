package ch05.ex05_10;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class ArrivalTimeCalculator {

	public static ZonedDateTime calcArrivalTime(String depatureZone, LocalDateTime depatureTime, String arrivalZone, Duration flightTime){
		ZoneId depatureZoneId = ZoneId.of(depatureZone);
		ZoneId arrivalZoneId = ZoneId.of(arrivalZone);
		ZonedDateTime zonedDepatureTime = ZonedDateTime.of(depatureTime, depatureZoneId);
		return zonedDepatureTime.plus(flightTime).withZoneSameInstant(arrivalZoneId);
	}
	
	public static void main(String[] args) {
		//ZoneId.getAvailableZoneIds().stream().filter(s -> s.contains("Europe")).forEach(System.out::println);
		String depatureZoneName = "America/Los_Angeles";
		LocalDateTime depatureTime = LocalDateTime.of(2015, 9, 27, 3, 5);
		String arrivalZoneName = "CET";
		Duration flightTime = Duration.ofHours(10).plusMinutes(50);
		ZonedDateTime arrivalTime = calcArrivalTime(depatureZoneName, depatureTime, arrivalZoneName, flightTime);
	
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
		System.out.println(formatter.format(arrivalTime));
		
	}

}
