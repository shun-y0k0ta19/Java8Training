package ch01.ex01_11;

public class PriorityMethodChecker {

	public interface Iabstract {
		public void f();
	}

	public interface Idefault {
		public default void f(){
			System.out.println("Idefault");
		}
	}

	public interface Istatic {
		public static void f(){
			System.out.println("Istatic");
		}
	}

	public interface Jabstract {
		public void f();
	}

	public interface Jdefault {
		public default void f(){
			System.out.println("Jdefault");
		}
	}
	
	public interface Jstatic {
		public static void f(){
			System.out.println("Jstatic");
		}
	}

	public class IabstractJabstract implements Iabstract, Jabstract{
		@Override
		public void f() {
			// 定義を強制される
		}
	}
	
	public class IabstractJdefault implements Iabstract, Jdefault{
		//コンフリクトのエラー
		@Override
		public void f(){
			System.out.println("redenifition");
		}
	}
	
	public class IabstractJstatic implements Iabstract, Jstatic{
		//staticメソッドは継承されなので影響なし
		@Override
		public void f() {
			// 定義を強制される
			
		}
	}
	
	public class IdefaultJdefault implements Idefault, Jdefault{
		//重複定義のエラー
		@Override
		public void f(){
			//再定義で解決
		}
	}
	
	public class IdefaultJstatic implements Idefault, Jstatic{
		//defaultメソッドは実装されており、staticメソッドは継承されないので重複せずエラーなし
	}
	
	public class IstaticJstatic implements Istatic, Jstatic{	
		//どちらもstaticメソッドしか持たないため、継承されない
	}
	
	public void run(){
		IdefaultJdefault ijd = new IdefaultJdefault();
		System.out.println(ijd);
	}
	
	public static void main(String[] args){
		
		
	}
	
}
