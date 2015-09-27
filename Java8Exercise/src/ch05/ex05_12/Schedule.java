package ch05.ex05_12;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.TreeSet;

public class Schedule {

	private Set<Plan> schedule;
	
	public Schedule(){
		schedule = new TreeSet<Plan>((p1, p2) -> p1.getTime().compareTo(p2.getTime()));
	}
	
	public void addPlan(Plan plan){
		schedule.add(plan);
	
	}
	
	public void addPlan(String subject, ZonedDateTime time){
		schedule.add(new Plan(subject, time));
	}
	
	public void checkBeforeAnHour(){
		schedule.stream()
		.filter(plan -> plan.getTime().isBefore(ZonedDateTime.now().plusHours(1)))
		.forEach(plan -> System.out.println(plan.toString()));
	}
	
	public static void main(String[] args) {
		Schedule schedule = new Schedule();
		schedule.addPlan("plan1", ZonedDateTime.now().plusHours(2));
		schedule.addPlan("plan2", ZonedDateTime.now().plusMinutes(30));
		schedule.checkBeforeAnHour();

	}

}
