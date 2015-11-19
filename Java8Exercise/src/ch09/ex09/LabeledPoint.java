package ch09.ex09;

import java.util.Objects;

public class LabeledPoint {
	private String label;
	private int x;
	private int y;
	
	@Override
    public boolean equals(Object obj) {
        LabeledPoint labeldPontObj = (LabeledPoint) obj;
        return Objects.equals(x, labeldPontObj.x) && Objects.equals(y, labeldPontObj.y) && Objects.equals(label, labeldPontObj.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, label);
    }

}
