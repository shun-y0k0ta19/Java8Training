package ch08.ex03;

public class EuclidGCD {

	public static int gcd(int x, int y) {
		if(x < y) {
			int tmp = x;
			x = y;
			y = tmp;
		}
		
		if(y == 0) {
			return x;
		}
		return 0;
	}
	
	private static int remWithPercent(int dividend, int divisor) {
		int rem = dividend % divisor;
		return Math.abs(rem);
	}
	
	private static int remWithFloorMod(int dividend, int divisor) {
		return Math.floorMod(dividend, divisor);
	}
	
	public static void main(String[] args) {
		System.out.println(remWithPercent(-4, 3));
		System.out.println(remWithFloorMod(4, -3));

	}

}
