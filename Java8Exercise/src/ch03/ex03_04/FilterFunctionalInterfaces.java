package ch03.ex03_04;

import javax.xml.stream.EventFilter;

public class FilterFunctionalInterfaces {

	EventFilter ef = (e) -> e.isStartElement();
	
}
