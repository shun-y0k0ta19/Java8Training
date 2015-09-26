package ch04.ex04_08;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

public class ControlPairUsingFXML implements Initializable{
	@FXML
	FXMLPerson p1;
	@FXML
	FXMLPerson p2;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println(p1.toString());
		System.out.println(p2.toString());
	}

	public static void main(String[] args) throws Exception{
		FXMLPair pair = FXMLLoader.load(ControlPairUsingFXML.class.getResource("PersonFXML.fxml"));
		System.out.println(pair.toString());
	}
	
}
