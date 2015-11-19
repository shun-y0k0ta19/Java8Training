package ch08.ex06;

import java.util.Comparator;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

public class Comparators {

    public static Comparator<Point2D> point2d() {
        return Comparator.comparing(Point2D::getX).thenComparing(Point2D::getY);
    }

    public static Comparator<Rectangle2D> rectangle2d() {
        return Comparator.comparing(Rectangle2D::getMaxX).thenComparing(Rectangle2D::getMinX)
                .thenComparing(Rectangle2D::getMaxY).thenComparing(Rectangle2D::getMinY);
    }

    public static void main(String[] args) {
        Point2D p1 = new Point2D(100, 200);
        Point2D p2 = new Point2D(200, 300);
        System.out.println(p1 + " " + p1 + ": " + point2d().compare(p1, p1));
        System.out.println(p1 + " " + p2 + ": " + point2d().compare(p1, p2));
        System.out.println(p2 + " " + p1 + ": " + point2d().compare(p2, p1));
        Rectangle2D r1 = new Rectangle2D(100, 200, 100, 200);
        Rectangle2D r2 = new Rectangle2D(200, 300, 200, 300);
        System.out.println(r1 + " " + r1 + ": " + rectangle2d().compare(r1, r1));
        System.out.println(r1 + " " + r2 + ": " + rectangle2d().compare(r1, r2));
        System.out.println(r2 + " " + r1 + ": " + rectangle2d().compare(r2, r1));
    }


}
