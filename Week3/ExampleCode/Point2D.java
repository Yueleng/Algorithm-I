public class Point2D {
    // one Comparator for each point (not static!)
    public final Comparator<Point2D> POLAR_ORDER = new PolarOrder();

    private final double x;
    private final double y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static int ccw(Point2D a, Point2D b, Point2D c) {
        double area2 = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
        if  (area2 < 0) return -1; // clockwise
        else if (area2 > 0) return +1; // counter-clockwise
        else return 0; // collinear;
    }

    private class PolarOrder implements Comparator<Point2D> {
        public int compare(Point2D q1, Point2D q2) {
            double dy1 = q1.y - y;
            double dy2 = q2.y - y;

            if (dy1 == 0 && dy2 == 0) {
                // compare x;
                double dx1 = q1.x - x;
                double dx2 = q2.x - x;
                if (dx1 < x && dx2 > x) return +1;
                if (dx1 > x && dx2 < x) return -1;
                return 0;
            } else if (dy1 >= 0 && dy2 < 0) 
                return -1;
            else if (dy2 >= 0 && dy1 < 0) 
                return +1;
            else 
                return -ccw(Point2D.this, q1, q2);
        }
    }
}