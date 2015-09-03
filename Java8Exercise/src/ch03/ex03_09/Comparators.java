package ch03.ex03_09;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.Objects;


public class Comparators {
	public static Comparator<Object> lexicographicComparator(String... fieldNames){
		@SuppressWarnings("unchecked")
		Comparator<Object> cmp = (obj1, obj2) -> {
			for(String fieldName : fieldNames){
				Object field1 = getField(obj1, fieldName);
				Object field2 = getField(obj2, fieldName);
				Objects.requireNonNull(field1, obj1 + " does not have " + fieldName);
				Objects.requireNonNull(field2, obj2 + " does not have " + fieldName);
				if(field1.equals(field2)){
				}
				else {
					try{
						return ((Comparable<Object>) field1).compareTo(field2); 
					} catch (ClassCastException e){
						throw new RuntimeException(fieldName + " is not Comparable", e);
					}
				}
			}
			return 0;
		};
		return cmp;
	}

	private static Object getField(Object obj, String fieldName){
		for(Class<?> clazz = obj.getClass(); clazz != null; clazz = clazz.getSuperclass()){
			Field field;
			try {
				field = clazz.getDeclaredField(fieldName);
				field.setAccessible(true);
				return field.get(obj);
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(" obj do not have such field.", e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException("Field is not Accesible.", e);
			} catch (NoSuchFieldException e) {
				// 親クラスを調べるためそのまま
			} catch (SecurityException e) {
				throw new RuntimeException("Getting field is security error.", e);
			}
		}
		return null;
	}
	
	public static void main(String[] args){
		Person john = new Person("John", 20);
		Person mark = new Person("Mark", 22);
		System.out.println(compare(john, mark, lexicographicComparator("name", "age")));
		System.out.println(compare(john, john, lexicographicComparator("name", "age")));
		
		Group group = new Group(john, mark);
		Group group2 = new Group(john, john);
		System.out.println(compare(group, group, lexicographicComparator("leader", "member")));
		System.out.println(compare(group, group2, lexicographicComparator("leader", "member")));
	}
	
	private static <T> int compare(T one, T two, Comparator<T> comparator){
		return comparator.compare(one, two);
		
	}
}
