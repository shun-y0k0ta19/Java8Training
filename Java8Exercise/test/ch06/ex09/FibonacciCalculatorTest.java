package ch06.ex09;

import static org.junit.Assert.*;

import org.junit.Test;

import ch06.ex09.FibonacciCalculator;

public class FibonacciCalculatorTest {

	@Test
	public void calcFibonacciTest() {
		int fibonacci = 1;
		for(int n = 0; fibonacci >= 0; n++) {
			int a;
			int b = 0;
			if(n < 2) {
				fibonacci = n;
				assertTrue("calcFibonacci method does not calculate correct fibonacci number.", 
						FibonacciCalculator.calcFibonacci(n) == n);
			}
			else {
				fibonacci = 1;
				int nn = n - 1;
				for(int i = 0; i < nn; i++) {
					a = b;
					b = fibonacci;
					fibonacci = a + b;
				}
				System.out.println(fibonacci);
				assertTrue("calcFibonacci method does not calculate correct fibonacci number.", 
						FibonacciCalculator.calcFibonacci(n) == fibonacci);
			}
		}
	}

}
