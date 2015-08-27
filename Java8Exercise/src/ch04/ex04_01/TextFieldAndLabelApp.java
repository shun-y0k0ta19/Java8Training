package ch04.ex04_01;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TextFieldAndLabelApp extends Application {

	@Override
	public void start(Stage primaryStage) {
		Label message = new Label("Hello, JavaFX!");
	    message.setFont(new Font(100));
	    
	    TextField messageField = new TextField(message.getText());
	    messageField.textProperty().addListener(property -> message.setText(messageField.getText()));
	
	    VBox root = new VBox();
	    root.getChildren().addAll(message, messageField);
	    primaryStage.setScene(new Scene(root));
	    primaryStage.setTitle("Ex04_01");
	    primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
