package ch04.ex04_10;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class BrowserApp extends Application {
	private WebView browser = new WebView();
	private WebEngine engine = browser.getEngine();
	private Button back = new Button("Back");
	private Button home = new Button("HOME");
	private TextField addressBar = new TextField();
	private String homeURL = "http://google.co.jp";
	
	@Override
	public void start(Stage stage) {
		
		engine.load(homeURL);
		engine.locationProperty().addListener(property -> addressBar.setText(engine.getLocation()));
		
		back.setMaxWidth(50);
		back.setOnAction(event -> engine.getHistory().go(-1));
		
		home.setMaxWidth(50);
		home.setOnAction(event -> engine.load(homeURL));
		
		addressBar.setText(homeURL);
		addressBar.setOnAction(event -> engine.load(addressBar.getText()));
		
		HBox toolBar = new HBox();
		VBox browserRayout = new VBox();
		
		
		addressBar.setMinWidth(600);
		toolBar.getChildren().addAll(back, addressBar, home);
		browserRayout.getChildren().addAll(toolBar, browser);
		
		stage.setScene(new Scene(browserRayout));
		stage.setTitle("MyBrowser");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
