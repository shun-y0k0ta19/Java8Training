package ch01.ex01_05;

public class MultiThreadAdder {
public static int data; 
	
	public static void addData(int num){
		data += num;
		System.out.println(Thread.currentThread().getName() + ": " + data);
	}
	
	public static void main(String[] args){
		Thread th1 = new Thread(() -> {
			while(true){
				try {
					MultiThreadAdder.addData(1);
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					return;
				}
			}
		});

		Thread th10 = new Thread(() -> {
			try{
				while(true){
					MultiThreadAdder.addData(10);
					Thread.sleep(1000);
				}
			}
			catch(InterruptedException e){
				return;
			}
		});

		Thread th100 = new Thread(() -> {
			try{
				while(true){
					MultiThreadAdder.addData(100);
					Thread.sleep(1000);
				}
			}
			catch(InterruptedException e){
				return;
			}
		});
		
		th1.start();
		th10.start();
		th100.start();
	}
}
