package ch03.ex03_11;

import javafx.scene.paint.Color;

public class ColorTransformerGenerator {
	public static ColorTransformer generate(int width, int height, int frameWidth, Color frameColor){
		ColorTransformer ct = (x, y, color) -> {
			if(x < frameWidth || y < frameWidth || x >= width - frameWidth || y >= height - frameWidth){
				return frameColor;
			}
			else {
				return color;
			}
		};
		return ct;
	}
}
