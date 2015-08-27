package ch03.ex03_08;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;


public class FrameGenerater {
	public static Image frameGenerate(Image in, ColorTransformer ct){
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++) {
	        for (int y = 0; y < height; y++) {
	            out.getPixelWriter().setColor(x, y, ct.apply(x, y, in.getPixelReader().getColor(x, y)));
	        }
	    }
	    return out;
	}
}
