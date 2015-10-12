package ch06.ex09;

import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.Objects;
import java.util.WeakHashMap;

/**
 * ２行２列の行列の要素を保持するクラス
 * Matrixオブジェクトは不変です。
 * @author design
 *
 */
public final class Matrix {
	private static final WeakHashMap<String, SoftReference<Matrix>> mObjMap = new WeakHashMap<>();
	private final String key;
	private final int[][] matrix;
	
	/**
	 * ２行２列の行列を表すMatrixオブジェクトを生成する
	 * @param matrix Matrixオブジェクトの要素を示す２次元配列
	 */
	private Matrix(int[][] matrix) {
		this.key = generateKey(matrix);
		this.matrix = copyOf(matrix);
	}

	/**
	 * ２行２列の行列を表すMatrixオブジェクトを生成する
	 * @param matrix ２行２列の行列を表す２次元配列
	 * @return 引数のパラメータを持つMatrixオブジェクト
	 */
	public static synchronized Matrix of(int[][] matrix) {
		String key = generateKey(matrix);
		SoftReference<Matrix> sRef;
		Matrix mObj;
		if((sRef = mObjMap.get(key)) != null) {
			if((mObj = sRef.get()) != null) {
				return mObj;
			}
		}
		mObj = new Matrix(matrix);
		sRef = new SoftReference<Matrix>(mObj);
		mObjMap.put(mObj.key, sRef);
		return mObj;
	}
	
	private static String generateKey(int[][] matrix) {
		String key = "";
		for(int[] column : matrix) {
			for(int elm : column) {
				key += elm;
			}
		}
		return key;
	}

	/**
	 * ２行２列の行列を表すMatrixオブジェクトを生成する
	 * @param a11 左上の要素の値
	 * @param a12 右上の要素の値
	 * @param a21 左下の要素の値
	 * @param a22 右下の要素の値
	 * @return 引数のパラメータを持つMatrixオブジェクト
	 */
	public static Matrix of(int a11, int a12, int a21, int a22) {
		int[][] matrix = new int[2][2];
		matrix[0][0] = a11;
		matrix[0][1] = a12;
		matrix[1][0] = a21;
		matrix[1][1] = a22;
		return of(matrix);
	}
		
	/**
	 * Matrixオブジェクトが保持する２行２列のint型の２次元配列を返す
	 * @return　Matrixオブジェクトが保持する２次元配列
	 */
	public int[][] get() {
		return copyOf(matrix);
	}
	
	/**
	 * ２次元配列のコビーオブジェクトを生成する
	 * @param matrix コピー元の2次元配列
	 * @return
	 */
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
		return Matrix.of(multipleMatrix(this.matrix, mObj.get()));
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
