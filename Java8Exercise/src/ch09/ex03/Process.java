package ch09.ex03;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;

public class Process {

	public void process(int flg) throws IOException {
		try {
			if(flg < 0) {
				throw new FileNotFoundException();
			} else if(flg == 0) {
				throw new UnknownHostException();
			}
		} catch (FileNotFoundException | UnknownHostException ex) {
			throw ex;
		}
	}
	
	public void process2(int flg) throws FileNotFoundException, UnknownHostException {
		try {
			if(flg < 0) {
				throw new FileNotFoundException();
			} else if(flg == 0) {
				throw new UnknownHostException();
			}
		} catch (FileNotFoundException | UnknownHostException ex) {
			throw ex;
		}
	}
}
