/**
 * Created by den on 12.02.15.
 */
import java.util.Comparator;

public class Point implements Comparable<Point> {

    private class BySlope implements Comparator<Point> {

        //compare two slopes from this point to point1 and this point to point2
        @Override
        public int compare(Point point1, Point point2) {
            double bySlope1 = Point.this.slopeTo(point1);
            double bySlope2 = Point.this.slopeTo(point2);
            if (bySlope1 < bySlope2) return -1;
            else if (bySlope1 > bySlope2) return 1;
            else return 0;
        }
    }

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new BySlope();       // YOUR DEFINITION HERE


    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        if (that.x == this.x && that.y != this.y) return Double.POSITIVE_INFINITY;
        else if (that.x == this.x && that.y == this.y) return Double.NEGATIVE_INFINITY;
        else if (that.y == this.y) return 0.0;
        else return ((double)(that.y - this.y) / (double)(that.x - this.x));
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (this.y < that.y)
            return -1;
        else if (this.y > that.y)
            return 1;
        else if (this.y == that.y && this.x < that.x)
            return -1;
        else if (this.y == that.y && this.x > that.x)
            return 1;
        else
            return 0;
    }

    // return string representation of this point
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}
