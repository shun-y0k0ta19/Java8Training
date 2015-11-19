package ch09.ex08;

public class Point implements Comparable<Point> {
	private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
	@Override
	public int compareTo(Point o) {
		if(x == o.x) {
			if( y == o.y) {
				return 0;
			}
			return y > o.y ? 1 : -1;
		}
		return x > o.x ? 1 : -1;
	}

}
