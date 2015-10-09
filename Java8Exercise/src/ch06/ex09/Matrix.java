package ch06.ex09;

import java.util.Arrays;
import java.util.Objects;

/**
 * ２行２列の行列の要素を保持するクラス
 * Matrixオブジェクトは不変です。
 * @author design
 *
 */
public final class Matrix {
	
	private final int[][] matrix;
	
	/**
	 * ２行２列の行列を表すMatrixオブジェクトを生成する
	 * @param a11 左上の要素の値
	 * @param a12 右上の要素の値
	 * @param a21 左下の要素の値
	 * @param a22 右下の要素の値
	 */
	public Matrix(int a11, int a12, int a21, int a22) {
		matrix = new int[2][2];
		matrix[0][0] = a11;
		matrix[0][1] = a12;
		matrix[1][0] = a21;
		matrix[1][1] = a22;
	}
	
	/**
	 * ２行２列の行列を表すMatrixオブジェクトを生成する
	 * @param matrix Matrixオブジェクトの要素を示す２次元配列
	 */
	public Matrix(int[][] matrix) {
		this.matrix = copyOf(matrix);
	}
	
	/**
	 * 2行２列の行列を表すMatrixオブジェクトを生成する
	 * @param mObj 新しいMatrixオブジェクトに持たせる要素を持つMatrixオブジェクト
	 */
	public Matrix(Matrix mObj) {
		Objects.requireNonNull(mObj, "mObj is null.");
		this.matrix = copyOf(mObj.matrix);

	}
	
	/**
	 * Matrixオブジェクトが保持する２行２列のint型の２次元配列を返す
	 * @return　Matrixオブジェクトが保持する２次元配列
	 */
	public int[][] get() {
		return copyOf(matrix);
	}
	
	private int[][] copyOf(int[][] matrix) {
		Objects.requireNonNull(matrix, "matrix is null.");
		int[][] newMatrix = new int[2][2];
		newMatrix[0] = Arrays.copyOf(matrix[0], 2);
		newMatrix[1] = Arrays.copyOf(matrix[1], 2);
		return newMatrix;
	}

	/**
	 * 行列の積を計算します
	 * multipleメソッドを呼び出すオブジェクトが被乗数、渡されるオブジェクトが乗数となります。
	 * @param mObj 乗数にあたるMatrixオブジェクト
	 * @return 積の結果となるMatrixオブジェクト
	 */
	public Matrix multiply(Matrix mObj) {
		return new Matrix(multipleMatrix(this.matrix, mObj.get()));
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

	@Override
	public String toString() {
		return "a11 = " + matrix[0][0] + ", a12 = " + matrix[0][1] + ", a21 = " + matrix[1][0]
				+ ", a22 = " + matrix[1][1];
	}
	
	@Override
	public boolean equals(Object obj) {
		if(Objects.isNull(obj)) {
			return false;
		}
		Matrix mObj = (Matrix) obj;
		if(this.matrix[0][0] == mObj.matrix[0][0]
				&& this.matrix[0][1] == mObj.matrix[0][1]
						&& this.matrix[1][0] == mObj.matrix[1][0]
								&& this.matrix[1][1] == mObj.matrix[1][1]) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		for(int[] array : matrix) {
			for(int elm : array) {
				result = 31 * result + elm;			
			}
		}
		System.out.println(result);
		return result;
	}
}
