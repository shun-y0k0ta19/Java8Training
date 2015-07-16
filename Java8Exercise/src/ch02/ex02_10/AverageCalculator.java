package ch02.ex02_10;

import java.util.stream.Stream;

public class AverageCalculator {
	
	public class Memory{
		public Double total = 0.0;
		public int count = 0;
		
		public Double calcAve(){
			return total / count;
		}
	}

	public Double calculateAverage(Stream<Double> dStream){
		Double res = dStream.reduce(new Memory(),
				(memory, db) -> {
					memory.total += db;
					memory.count++;
					return memory;
				}, (memory1, memory2) -> {
					memory1.total += memory2.total;
					memory1.count += memory2.count;
					return memory1;
				}).calcAve();
				
		return res;
		
	}
	
	public static void main(String[] args) {
		Double[] values = {1.0, 2.0, 3.0, 4.0, 5.0};
		AverageCalculator ac = new AverageCalculator();
		System.out.println(ac.calculateAverage(Stream.of(values)));

	}

}
