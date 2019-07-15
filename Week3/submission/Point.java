import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public String toString() {
        return '(' + x + ", " + y + ")";
    }

    public int compareTo(Point that) {
        // compare two points by y-coordinates, breaking ties by x-coordinates
        // the invoking point (x_0, y_0) is less than the argument point (x_1, y_1) 
        // if and only if either y_0 < y_1 or if y_0 = y_1 and x_0 < x_1;
        if (this.x == that.x && this.y == that.y) return 0;
        if ( (this.y < that.y) || (this.y == that.y && this.x < that.x) ) return -1;
        return 1;
    }

    public double slopeTo(Point that) {
        // formula: (y_1 - y_0) / (x_1 - x_0);
        // treat the slope of a horizonal line segment as positive 
        // treat the slope of a vertical line segment as positive infinity
        // treat the slope of a degenerate line segment as negative infinity;
        if (this.x == that.x && this.y == that.y) return Double.NEGATIVE_INFINITY; // denegerate line segment.
        if (this.x == that.x) return Double.POSITIVE_INFINITY; // horizontal line.
        if (this.y == that.y) return 0;
        return 1.0 * (that.y - this.y) / (that.x - this.x);
    }

    // compare two points by slopes they make with this point;
    public Comparator<Point> slopeOrder() {
        return new PointComparator();
    }

    private class PointComparator implements Comparator<Point> {
        //  formally, the point (x_1, y_1) is less than the point (x_2, y_2) if and only if
        // slopeTo(a) is less than slopeTo(b). Treat horizontal, vertical, and degenerate line segments
        // as in the slopeTo() method.
        public int compare(Point a, Point b) {
            double diff = slopeTo(a) - slopeTo(b);
            if (diff > 0) return 1;
            if (diff < 0) return -1;
            return 0;
        }
    }

    public static void main(String[] args) {
        // unit test
    }
}
