package ch06.ex09;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import ch06.ex09.Matrix;

public class MatrixTest {

	@Test
	public void getMethodTest() {
		int[][] matrix = {{1, 2}, {3, 4}};
		Matrix mObj = Matrix.of(matrix);
		assertEquals("mObj does not equal Matrix obj created matrix(2-dimension array).", mObj, Matrix.of(matrix));
		assertFalse("mObj retruns actual array it has.", Matrix.of(matrix).get().equals(matrix));
	}

	@Test
	public void equalsMethodTest() {
		Matrix mObj = Matrix.of(1, 2, 3, 4);
		Matrix sameMObj = Matrix.of(1, 2, 3, 4);
		Matrix otherMObj = Matrix.of(1, 1, 3, 4);
		int[][] matrix = {{1, 2}, {3, 4}};

		assertTrue(mObj.equals(mObj));
		assertTrue(mObj.equals(sameMObj));
		assertTrue(sameMObj.equals(mObj));
		assertFalse(mObj.equals(otherMObj));
		assertFalse(otherMObj.equals(mObj));
		assertFalse(mObj.equals(null));

		assertTrue("Matrixes' equals methods does not work correctly.", Matrix.of(matrix).equals(Matrix.of(matrix)));
	}
	
	@Test
	public void classMatrixIsImmutable() {
		int[][] mat1 = setMatrixArray(1, 1, 1, 1);
		Matrix mObj1 = Matrix.of(mat1);
		mat1[0][0] = 2;
		assertTrue("Matrix class is not immutable.", mObj1.get()[0][0] == 1);
		
		int[][] mat2 = mObj1.get();
		mat2[0][0] = 2;
		assertTrue("Matrix class is not immutable.", mObj1.get()[0][0] == 1);
		
		Matrix mObj2 = Matrix.of(setMatrixArray(0, 0, 0, 0));
		Matrix mObj3 = Matrix.of(setMatrixArray(0, 0, 0, 0));
		Matrix mObjResult = mObj2.multiply(mObj3);
		int[][] resultMat = mObjResult.get();
		resultMat[0][0] = 1;
		assertTrue("Matrix class is not immutable.", mObj2.get()[0][0] == 0);
		assertTrue("Matrix class is not immutable.", mObj3.get()[0][0] == 0);
	}
	
	@Test
	public void checkMatrixCach() {
		Matrix mObj = Matrix.of(1, 2, 3, 4);
		Matrix sameMObj = Matrix.of(1, 2, 3, 4);
		assertTrue("Matrix cach does not work correctly.", mObj == sameMObj);
	
	}

	@Test
	public void hashCodeMethodTest() {
		final int ATTEMPTS = 100;
		Random r = new Random(0);
		Matrix mObj1;
		Matrix mObj2;

		for(int i = 0; i < ATTEMPTS; i++) {
			int[][] matrix = createRandomMatrix(r);
			mObj1 = Matrix.of(matrix);
			mObj2 = Matrix.of(matrix);
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
				Matrix.of(mat1).multiply(Matrix.of(mat2)) == (Matrix.of(answer)));
		
		for(int ai = 0 ; ai < ATTEPTS; ai++) {
			mat1 = createRandomMatrix(r);
			mat2 = createRandomMatrix(r);
			answer = multipleMatrix(mat1, mat2);
			assertTrue("multiple method does not return correct result.", 
					Matrix.of(mat1).multiply(Matrix.of(mat2)).equals(Matrix.of(answer)));
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
