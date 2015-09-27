package ch05.ex05_08;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
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
		LocalDateTime now = LocalDateTime.now();
		ZonedDateTime utcNow = ZonedDateTime.of(now, Clock.systemUTC().getZone());
		
		ZoneId.getAvailableZoneIds().stream()
		.map(ZoneId::of)
		.map(zoneId -> ZonedDateTime.of(now, zoneId))
		.map(zonedTime -> Duration.between(zonedTime, utcNow))
		.forEach(duration -> System.out.println(durationFormat(duration)));
	}

}
