package ch03.ex03_11;

import javafx.scene.paint.Color;

@FunctionalInterface
public interface ColorTransformer {
	Color apply(int x, int y, Color colorAtXY);
}
