package ch06.ex11;

import java.net.PasswordAuthentication;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Repeater {

	public static <T> CompletableFuture<T> repeat(Supplier<T> action, Predicate<T> until) {
		return CompletableFuture.supplyAsync(action).thenComposeAsync(t -> {
			if(until.test(t)) {
				return CompletableFuture.completedFuture(t);
			} else {
				return repeat(action, until);
			}
		});
	}

	public static void main(String[] args) {
		PasswordAuthentication pa = new PasswordAuthentication("yokota", "secret".toCharArray());

		Supplier<String> action = () -> {
			Scanner scanner = new Scanner(System.in);
			String pass = "";
			for(;;){
				System.out.println(Thread.currentThread().getId() + " input password: ");
				if(scanner.hasNextLine()) {
					pass = scanner.nextLine();
					break;
				}
			}
			scanner.close();
			return pass;
		};

		Predicate<String> until = pass -> {
			System.out.println(Arrays.equals(pa.getPassword(), pass.toCharArray()));
			return (Arrays.equals(pa.getPassword(), pass.toCharArray()));
		};
		CompletableFuture<String> result = repeat(action, until);
		for(;;) {
			try {
				System.out.println("match!" + result.get(1, TimeUnit.SECONDS));
				break;
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TimeoutException e) {
				//一秒スリープ
			}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
