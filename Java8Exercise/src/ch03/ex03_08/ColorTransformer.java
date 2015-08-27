package ch03.ex03_08;

import javafx.scene.paint.Color;

@FunctionalInterface
public interface ColorTransformer {
	Color apply(int x, int y, Color colorAtXY);
}
