package ch01.ex01_07;

public class SequentialExecutor {
	
	/**
	 * firstExecの後にsecondExecを実行するRunnableを作る。
	 * @param firstExec 最初に実行する処理
	 * @param nextExec 二番目に実行する処理
	 * @return firstExecのrun()メソッドの次にsecondExecのrun()メソッドを実行する
	 */
	public static Runnable andThen(Runnable firstExec, Runnable nextExec){
		return () -> {
			firstExec.run();
			nextExec.run();
		};
	}
	
	
	public static void main(String[] args){
		Runnable r = andThen(() -> System.out.println("First!"),
				() -> System.out.println("Second!"));
		new Thread(r).start();
	}
}
