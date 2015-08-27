package ch03.ex03_12;

import java.util.function.UnaryOperator;

import javafx.scene.paint.Color;

public class ColorTransformerGenerator {
	public static ColorTransformer frame(int width, int height, int frameWidth, Color frameColor){
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
	
	public static ColorTransformer color(UnaryOperator<Color> op){
		return (x, y, color) -> op.apply(color);
	}
	
	public static ColorTransformer compose(ColorTransformer ct, UnaryOperator<Color> op){
		return (x, y, color) -> ct.apply(x, y, op.apply(color));
	}
}
