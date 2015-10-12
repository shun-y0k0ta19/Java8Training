package ch06.ex09;

import java.util.Arrays;

public class FibonacciCalculator {
	
	/**
	 * 指定された番数のフィボナッチ数を計算する
	 * @param n フィボナッチ数列のn番目を示す整数
	 * @return 指定された番数のフィボナッチ数
	 */
	public static int calcFibonacci(int n) {
		if(n < 0 ) {
			throw new IllegalArgumentException("n is less than 0.");
		}
		if(n < 2) {
			return n;
		}
		n--;
		Matrix[] matrixArray = new Matrix[n];
		Matrix matrixA = Matrix.of(1, 1, 1, 0);
		Arrays.parallelSetAll(matrixArray, index -> matrixA);
		Arrays.parallelPrefix(matrixArray, Matrix::multiply); 
		return matrixArray[n - 1].get()[0][0];
	}
	
	public static void main(String[] args) {
		for(int n = 0; n < 47; n++) {
			System.out.println(calcFibonacci(n));
		}
	}
	
}
