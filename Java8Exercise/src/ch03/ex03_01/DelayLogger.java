package ch03.ex03_01;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

public class DelayLogger {

	private Logger logger;
	
	public DelayLogger(Logger logger){
		this.logger = logger;
	}
	
	public void setLevel(Level level){
		logger.setLevel(level);
	}
	
	public void logIf(Level level, BooleanSupplier condition, Supplier<String> message){
		if(logger.isLoggable(level)){
			if(condition.getAsBoolean()){
				logger.log(level, message.get());
			}
		}
	}
	
	public static void main(String[] args){
		Logger logger = Logger.getGlobal();
		logger.addHandler(new StreamHandler(){
			{ 
		    	setOutputStream(System.out); 
		    }
		});
		logger.setUseParentHandlers(false);
		
		DelayLogger dLogger = new DelayLogger(logger);
		dLogger.setLevel(Level.FINE);
		dLogger.logIf(Level.INFO, () -> true, () -> "Level Fine");
		dLogger.logIf(Level.SEVERE, () -> true, () -> "Level Severe");
	}
	
}
