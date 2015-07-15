package ch01.ex01_09;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * 
 * @author Shun Yokota
 * Collection<E>インターフェースを継承したCollection2インターフェース<br>
 * forEachIfメソッドをデフォルトメソッドとして実装
 * @param <E> Collection内で保持するインスタンスの型
 */
public interface Collection2<E> extends Collection<E> {
	/**
	 * filterでtrueを返す要素のみにactionを適用するメソッド
	 * @param action 各要素に適用する処理
	 * @param filter 各要素にactionを適用するかどうかを判定する処理
	 */
	public default void forEachIf(Consumer<E> action, Predicate<E> filter){
		for(E obj : this){
			if(filter.test(obj)){
				action.accept(obj);
			}
		}
	}
}

/*
コレクション内の特定の要素をクロージャ内で変更を加えることができる。
*/
