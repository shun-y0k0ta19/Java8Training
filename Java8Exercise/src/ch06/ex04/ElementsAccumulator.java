package ch06.ex04;

import static MyUtilities.FileUtils.*;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.Function;

public class ElementsAccumulator<E> {

	public long extractMax(Collection<? extends E> elements, Function<? super E, Long> toLong) {
		LongAccumulator maxFinder = new LongAccumulator(
				(observed, newVal) -> observed > newVal ? observed : newVal, Long.MIN_VALUE);

		elements.stream().parallel().forEach(elm -> maxFinder.accumulate(toLong.apply(elm)));
		return maxFinder.get();
	}

	public long extractMin(Collection<? extends E> elements, Function<? super E, Long> toLong) {
		LongAccumulator minFinder = new LongAccumulator(
				(observed, newVal) -> observed < newVal ? observed : newVal, Long.MAX_VALUE);

		elements.stream().parallel().forEach(elm -> minFinder.accumulate(toLong.apply(elm)));
		return minFinder.get();
	}

	public static void main(String[] args){

		ElementsAccumulator<String> eAccumulator = new ElementsAccumulator<>();
		List<String> wordList = createWordList("src/files/war-and-peace.txt");
		
		long maxWordChars = eAccumulator.extractMax(wordList, s -> Long.valueOf(s.length()));
		System.out.println(maxWordChars);

		long minWordChars = eAccumulator.extractMin(wordList, s -> Long.valueOf(s.length()));
		System.out.println(minWordChars);

	}

}
