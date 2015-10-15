package ch06.ex10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class WebCrawler {

	public String blockingReadPage(URL url) {
		StringBuilder strBuilder = new StringBuilder();

		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
	        String line;
	        while ((line = reader.readLine()) != null) {
	            strBuilder.append(line);
	            strBuilder.append('\n');
	        }
	    } catch (IOException ex) {
	        throw new RuntimeException(ex);
	    }
	    return strBuilder.toString();
	}
	
	public static void main(String[] args) {
		WebCrawler wc = new WebCrawler();
		URL url;
		try {
			url = new URL("http://r.goope.jp/k-mizuno");
		} catch (MalformedURLException e) {
			throw new RuntimeException("URL is not generated.", e);
		}
		CompletableFuture<List<URL>> linkListFuture = CompletableFuture
				.supplyAsync(() -> wc.blockingReadPage(url))
				.thenApplyAsync(Parser::getLinks);
		ForkJoinPool.commonPool().awaitQuiescence(10, TimeUnit.SECONDS);
		try {
			linkListFuture.get().forEach(System.out::println);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

	}

}
