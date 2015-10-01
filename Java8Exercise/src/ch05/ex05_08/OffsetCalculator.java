package ch05.ex05_08;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class OffsetCalculator {
	
	public static String durationFormat(Duration duration){
		if(duration.isNegative()){
			return String.format("%03d:%02d", duration.toHours(), duration.toMinutes()%60);
		}
		else{
			return String.format("+%02d:%02d", duration.toHours(), duration.toMinutes()%60);			
		}
	}
	
	public static void main(String[] args) {
		
		ZoneId.getAvailableZoneIds().stream()
		.map(zoneId -> ZonedDateTime.now(ZoneId.of(zoneId)).getOffset())
		//.map(zonedTime -> Duration.between(zonedTime, utcNow))
		.forEach(zoneOffset -> System.out.println(zoneOffset.toString()));
		//.forEach(duration -> System.out.println(durationFormat(duration)));
	}

}
