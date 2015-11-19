package ch08.ex01;

public class UnsignedCalculator {

	public static long add(int num1, int num2) {
		return Integer.toUnsignedLong(num1 + num2);
	}
	
	public static long subs(int num1, int num2) {
		return Integer.toUnsignedLong(num1 - num2);
	}
	
	public static long multiply(int num1, int num2) {
		return Integer.toUnsignedLong(num1) * Integer.toUnsignedLong(num2);
	}
	
	public static long divide(int num1, int num2) {
		return Long.divideUnsigned(num1, num2);
	}
	
	public static long remainder(int num1, int num2) {
		return Long.remainderUnsigned(num1, num2);
	}
	
	public static int compare(int num1, int num2) {
		return Long.compareUnsigned(num1, num2);
	}
	
	public static void main(String[] args) {
		int num1 = 3;
		int num2 = Integer.MAX_VALUE;
		System.out.println(add(num1, num2));
		System.out.println(subs(num1, num2));
		System.out.println(multiply(num1, num2));
		System.out.println(divide(num2, num1));
	}
	
}
