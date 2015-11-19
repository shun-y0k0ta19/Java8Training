package ch09.ex10;

import java.util.Objects;

public class LabeledPoint implements Comparable<LabeledPoint>{
	private String label;
	private int x;
	private int y;
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	@Override
    public boolean equals(Object obj) {
        LabeledPoint labeldPontObj = (LabeledPoint) obj;
        return Objects.equals(x, labeldPontObj.x) && Objects.equals(y, labeldPontObj.y) && Objects.equals(label, labeldPontObj.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, label);
    }

    @Override
    public int compareTo(LabeledPoint obj) {
        Objects.requireNonNull(obj);
        int res = Integer.compare(x, obj.getX());
        if (res != 0) {
            return res;
        }
        return Integer.compare(y, obj.getY());
    }
}
