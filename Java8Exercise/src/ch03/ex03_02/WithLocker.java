package ch03.ex03_02;

import java.util.concurrent.locks.ReentrantLock;

public class WithLocker {

	public static void withLock(ReentrantLock mylock, Runnable r){
		try{
			r.run();
		} finally{
			mylock.unlock();
		}
	}
}
