package ch05.ex05_11;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class FlightTimeCalculator {

	public static Duration calcFlightTime(String depatureZone, LocalDateTime depatureTime, String arrivalZone, LocalDateTime arrivalTime){
		ZoneId depatureZoneId = ZoneId.of(depatureZone);
		ZoneId arrivalZoneId = ZoneId.of(arrivalZone);
		ZonedDateTime zonedDepatureTime = ZonedDateTime.of(depatureTime, depatureZoneId);
		ZonedDateTime zonedArrivalTime = ZonedDateTime.of(arrivalTime, arrivalZoneId);
		
		return Duration.between(zonedDepatureTime, zonedArrivalTime);
	}
	
	public static String durationFormat(Duration duration){
		if(duration.isNegative()){
			return String.format("%03d:%02d", duration.toHours(), duration.toMinutes()%60);
		}
		else{
			return String.format("+%02d:%02d", duration.toHours(), duration.toMinutes()%60);			
		}
	}
	
	public static void main(String[] args) {
		String depatureZoneName = "CET";
		LocalDateTime depatureTime = LocalDateTime.of(2015, 9, 27, 14, 5);
		String arrivalZoneName = "America/Los_Angeles";
		LocalDateTime arrivalTime = LocalDateTime.of(2015, 9, 27, 16, 40);
		Duration flightTime = calcFlightTime(depatureZoneName, depatureTime, arrivalZoneName, arrivalTime);
	
		System.out.println(durationFormat(flightTime));
		
	}

}
