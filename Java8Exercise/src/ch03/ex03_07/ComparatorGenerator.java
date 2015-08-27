package ch03.ex03_07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class ComparatorGenerator {
	public enum Order{ASC, DSC};
	public enum Case{SENSITIVE, INSENSITIVE};
	public enum Space{CONTAIN, EXCLUDE};
	public static Comparator<String> generate(Order order, Case ccase, Space space){
		Comparator<String> cmp = (s1, s2) -> {
			if(ccase == Case.INSENSITIVE){
				s1 = s1.toLowerCase();
				s2 = s2.toLowerCase();
			}
			if(space == Space.EXCLUDE){
				s1 = s1.replaceAll(" ", "");
				s2 = s2.replaceAll(" ", "");
			}
			return order == Order.ASC ? s1.compareTo(s2) : -s1.compareTo(s2);
		};
		
		return cmp;
	}
	
	public static void main(String[] args){
		String[] words = { "Yokota", "yakata", "y o k o t a", "akata"};
		Arrays.sort(words, generate(Order.ASC, Case.SENSITIVE, Space.EXCLUDE));
		Stream.of(words).forEach(System.out::println);
		
		System.out.println();
		Arrays.sort(words, generate(Order.ASC, Case.SENSITIVE, Space.CONTAIN));
		Stream.of(words).forEach(System.out::println);
		
		System.out.println();
		Arrays.sort(words, generate(Order.ASC, Case.INSENSITIVE, Space.CONTAIN));
		Stream.of(words).forEach(System.out::println);
		
	}
}
