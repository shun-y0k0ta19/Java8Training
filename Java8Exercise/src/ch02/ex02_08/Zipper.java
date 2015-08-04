package ch02.ex02_08;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Zipper {

	private static final int STRING_NUM = 10;
	
	/**
	 * firstとsecondの要素を交互に取り出すStream<T>を返します。<br>
	 * 数が同じでない場合は、どちらかのストリームから要素がなくなった時点の要素を返します。
	 * @param first 先に要素を取り出すストリーム
	 * @param second 後に要素を取り出すストリーム
	 * @return firstとsecondの要素が交互に格納されたStream<T>です。
	 * @throws NullPointerException 引数がnullである場合
	 */
	public static <T> Stream<T> zip (Stream<T> first, Stream<T> second){
		Objects.requireNonNull(first, "first is Null.");
		Objects.requireNonNull(second, "second is Null.");
		
		Spliterator<T> firstSpliterator = first.spliterator();
		Spliterator<T> secondSpliterrator = second.spliterator();
		Stream.Builder<T> stb = Stream.builder();
		boolean hasNext = true;
		while(hasNext){
			hasNext = firstSpliterator.tryAdvance(stb::add);
			if(!hasNext){
				break;
			}
			hasNext = secondSpliterrator.tryAdvance(stb::add);
		}
		return stb.build();
		
		/*
		Spliterator<T> sptr = stb.build().spliterator();
		System.out.println(sptr.characteristics());
		return StreamSupport.stream(sptr, false);
		*/
	}
	
	public static void main(String[] args) {
		List<String> first = new ArrayList<>();
		List<String> second = new ArrayList<>();
		for(int i = 0; i < STRING_NUM; i++){
			first.add("first" + i);
			second.add("second" + i);
		}
		Stream<String> result = zip(first.stream().limit(6), second.stream().limit(5));
		result.forEach(System.out::println);
	}

}
