package ch03.ex03_08;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FramedImageApp extends Application {

	@Override
	public void start(Stage primaryStage) {
		Image image = new Image("ch03/ex03_05/queen-mary.png");
		Image framedImage = FrameGenerater.frameGenerate(image, 
				ColorTransformerGenerator.generate(
						(int)image.getWidth(), 
						(int)image.getHeight(), 
						15, 
						Color.BLUE
				));
		primaryStage.setScene(new Scene(new HBox(
				new ImageView(image),
				new ImageView(framedImage)
				)));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
