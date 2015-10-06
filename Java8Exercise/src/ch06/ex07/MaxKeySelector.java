package ch06.ex07;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class MaxKeySelector {

	public static String selectMaxKey(ConcurrentHashMap<String, Long> map) {
		Entry<String, Long> maxEntry = map.reduceEntries(1000, (max, observed) -> max.getValue() > observed.getValue() ? max : observed);
		return maxEntry.getKey();
	}
	
	public static void main(String[] args) {
		ConcurrentHashMap<String, Long> tmpmap = new ConcurrentHashMap<>();
		tmpmap.put("one", 1L);
		tmpmap.put("two", 2L);
		tmpmap.put("three", 3L);
		tmpmap.put("third", 3L);
		
		System.out.println(selectMaxKey(tmpmap));
	}

}
