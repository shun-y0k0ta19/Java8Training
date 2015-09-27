package ch05.ex05_09;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.stream.Stream;

public class OffsetCalculator2 {
	
	public static String durationFormat(Duration duration){
		if(duration.isNegative()){
			return String.format("%03d:%02d", duration.toHours(), duration.toMinutes()%60);
		}
		else{
			return String.format("+%02d:%02d", duration.toHours(), duration.toMinutes()%60);			
		}
	}
	
	public static Stream<Duration> calcOffsetFromUTC() {
		LocalDateTime now = LocalDateTime.now();
		ZonedDateTime utcNow = ZonedDateTime.of(now, Clock.systemUTC().getZone());
		
		return ZoneId.getAvailableZoneIds().stream()
				.map(ZoneId::of)
				.map(zoneId -> ZonedDateTime.of(now, zoneId))
				.map(zonedTime -> Duration.between(zonedTime, utcNow));
	}

	public static Stream<ZoneId> minutesOffsetZoneStream(){
		LocalDateTime now = LocalDateTime.now();
		ZonedDateTime utcNow = ZonedDateTime.of(now, Clock.systemUTC().getZone());
		
		return ZoneId.getAvailableZoneIds().stream()
				.map(ZoneId::of)
				.map(zoneId -> ZonedDateTime.of(now, zoneId))
				.filter(zonedTime -> {
					long duration = Duration.between(zonedTime, utcNow).toMinutes();
					return (duration % 60) != 0;
				})
				.map(ZonedDateTime::getZone);
	}
	
	public static void main(String[] args) {
		minutesOffsetZoneStream().forEach(System.out::println);
	}


}
