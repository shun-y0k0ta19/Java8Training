package ch06.ex08;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.LongStream;

//ほとんどのケースでParallelSortの方が速い。
//配列数が〜30000個程度の場合、 sortの方が速いこともある。
//計算時間の平均を取るための回数を増やすと最適化(？)のため、平均ソート時間が短くなる上、
//parallelSortの方が速くなる。

public class SortTimeMeasure {
	private static final int ATTEMPT_FOR_SORT_TIME_AVE = 30;

	public static long calcFastArrayNum(int arrayNum) {
		double sortTimeAve = 0;
		double parallelSortTimeAve = 0;
		Random random = new Random(10000000);

		while(sortTimeAve <= parallelSortTimeAve) {
			String[] array = createRandomStringArray(arrayNum, random);
			
			sortTimeAve = calcSortTimeAve(array, Arrays::sort);
			System.out.printf("        SortTime: %8.0f%n", sortTimeAve);

			parallelSortTimeAve = calcSortTimeAve(array, Arrays::parallelSort);
			System.out.printf("ParallelSortTime: %8.0f%n", parallelSortTimeAve);
			arrayNum++;
		}
		return --arrayNum;
	}

	private static <T> double calcSortTimeAve(T[] array, Consumer<T[]> sort) {
		long[] sortTimes = new long[ATTEMPT_FOR_SORT_TIME_AVE];
		
		for(int i = 0; i < ATTEMPT_FOR_SORT_TIME_AVE; i++) {
			T[] attemptArray = Arrays.copyOf(array, array.length);
			sortTimes[i] = arrayOperatingTime(attemptArray, sort);
		}
		return LongStream.of(Arrays.copyOfRange(sortTimes, 2, ATTEMPT_FOR_SORT_TIME_AVE))
				//.peek(System.out::println)
				.average()
				.getAsDouble();
	}

	private static String[] createRandomStringArray(int arrayNum, Random random) {
		String[] array = new String[arrayNum];
		for(int i = 0; i < array.length; i++) {
			array[i] = String.valueOf(random.nextInt());
		}
		return array;
	}

	public static <T> long arrayOperatingTime(T[] array1, Consumer<T[]> sort) {
		long start, end;
		start = System.nanoTime();
		sort.accept(array1);
		end = System.nanoTime();
		return end - start;
	}
	
	public static void main(String[] args) {
		int arrayNum = 30000;
		System.out.println(calcFastArrayNum(arrayNum));
	}

}
