package ch03.ex03_06;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TransformImageApp extends Application {

	@Override
	public void start(Stage primaryStage) {
		Image image = new Image("ch03/ex03_06/queen-mary.png");
		Image transformedImage = ImageTransformer.transform(image, 
				(color, factor) -> color.deriveColor(0, 1, factor, 1),
				1.5);
		primaryStage.setScene(new Scene(new HBox(
				new ImageView(image),
				new ImageView(transformedImage)
				)));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
