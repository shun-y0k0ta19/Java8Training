package ch01.ex01_11;


public class PriorityMethodChecker2 {
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

	public abstract class ParentAbstract {
		public abstract void f();
	}

	public class Parent {
		public void f(){
			System.out.println("parent");
		}
	}

	public static class ParentStatic {
		public static void f(){
			System.out.println("parentStatic");
		}
	}

	public class IabstractExtendsParentAbstract extends ParentAbstract implements Iabstract{
		//未定義でコンパイルエラー
		@Override
		public void f() {
			// ここで定義するかabstractクラスとする
		}
	}

	public class IdefaultExtendsParentAbstract extends ParentAbstract implements Idefault{
		//IdefaultはParentAbstractの抽象メソッドの実装を解決しない
		@Override
		public void f() {
			// TODO Auto-generated method stub

		}
	}

	public class IstaticExtendsParentAbstract extends ParentAbstract implements Istatic{
		//未定義でコンパイルエラー
		//インターフェースのstaticメソッドは継承されない
		@Override
		public void f() {
			// TODO Auto-generated method stub

		}

	}
	
	public class IabstractExtendsParent extends Parent implements Iabstract{
	}
	public class IdefaultExtendsParent extends Parent implements Idefault{
	}
	public class IstaticExtendsParent extends Parent implements Istatic{
	}

	/*
	public class IabstractExtendsParentStatic extends ParentStatic implements Iabstract{
		//コンフリクトでコンパイルエラー
		
	}
*/
	/*
	public class IdefaultExtendsParentStatic extends ParentStatic implements Idefault{
		//コンフリクトでコンパイルエラー
	}
*/
	public class IstaticExtendsParentStatic extends ParentStatic implements Istatic{
		//インターフェースのstaticメソッドは継承されないのでParentStaticのメソッドのみ継承される
	}
	
	public void exe(){
		IabstractExtendsParent iaep = new IabstractExtendsParent();
		iaep.f();
		IdefaultExtendsParent idep = new IdefaultExtendsParent();
		idep.f();
		IstaticExtendsParent isep = new IstaticExtendsParent();
		isep.f();
	}

	public static void main(String[] args){
		PriorityMethodChecker2 pmc2 = new PriorityMethodChecker2();
		pmc2.exe();
	}
	
}
