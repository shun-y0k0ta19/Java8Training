package ex09;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import ch06.ex09.Matrix;

public class MatrixTest {

	@Test
	public void getMethodTest() {
		int[][] matrix = {{1, 2}, {3, 4}};
		Matrix mObj = new Matrix(matrix);
		assertEquals("mObj does not equal Matrix obj created matrix(2-dimension array).", mObj, new Matrix(matrix));
		assertFalse("mObj retruns actual array it has.", new Matrix(matrix).get().equals(matrix));
	}

	@Test
	public void equalsMethodTest() {
		Matrix mObj = new Matrix(1, 2, 3, 4);
		Matrix sameMObj = new Matrix(1, 2, 3, 4);
		Matrix otherMObj = new Matrix(1, 1, 3, 4);
		int[][] matrix = {{1, 2}, {3, 4}};

		assertTrue(mObj.equals(mObj));
		assertTrue(mObj.equals(sameMObj));
		assertTrue(sameMObj.equals(mObj));
		assertFalse(mObj.equals(otherMObj));
		assertFalse(otherMObj.equals(mObj));
		assertFalse(mObj.equals(null));

		assertTrue(new Matrix(matrix).equals(new Matrix(matrix)));
		assertFalse(new Matrix(matrix) == new Matrix(matrix));
	}

	@Test
	public void hashCodeMethodTest() {
		final int ATTEMPTS = 100;
		Random r = new Random(0);
		Matrix mObj1;
		Matrix mObj2;

		for(int i = 0; i < ATTEMPTS; i++) {
			int[][] matrix = createRandomMatrix(r);
			mObj1 = new Matrix(matrix);
			mObj2 = new Matrix(matrix);
			assertTrue("mObj1 and mObj2 is not same object.", mObj1.equals(mObj2));
			assertEquals("They do not have same hashcode.", mObj1.hashCode(), mObj2.hashCode());
		}
	}

	private int[][] createRandomMatrix(Random r) {
		int[][] matrix = new int[2][2];
		for(int ci = 0; ci < 2; ci++) {
			for(int ri = 0; ri < 2; ri++) {
				matrix[ci][ri] = r.nextInt(1000);
			}
		}
		return matrix;
	}

	@Test
	public void multipulMethodTest() {
		final int ATTEPTS = 100;
		Random r = new Random(0);
		int[][] mat1 = setMatrixArray(1, 2, 3, 1);
		int[][] mat2 = setMatrixArray(4, 5, 0, 7);
		int[][] answer = setMatrixArray(4, 19, 12, 22);
		assertTrue("multiple method does not return correct result.", 
				new Matrix(mat1).multiply(new Matrix(mat2)).equals(new Matrix(answer)));
		
		for(int ai = 0 ; ai < ATTEPTS; ai++) {
			mat1 = createRandomMatrix(r);
			mat2 = createRandomMatrix(r);
			answer = multipleMatrix(mat1, mat2);
			assertTrue("multiple method does not return correct result.", 
					new Matrix(mat1).multiply(new Matrix(mat2)).equals(new Matrix(answer)));
		}
	}

	private int[][] multipleMatrix(int[][] mat1, int[][] mat2) {
		int[][] answer = new int[2][2];
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				for(int k = 0; k < 2; k++) {
					answer[i][j] += mat1[i][k] * mat2[k][j];
				}
			}
		}
		return answer;
	}
	
	private int[][] setMatrixArray(int a, int b, int c, int d) {
		int[][] matrix = new int[2][2];
		matrix[0][0] = a;
		matrix[0][1] = b;
		matrix[1][0] = c;
		matrix[1][1] = d;
		return matrix;
	}
}
