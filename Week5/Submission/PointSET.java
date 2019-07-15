import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class PointSET {
    private TreeSet<Point2D> points;

    public PointSET() {
        // construct an empty set of points
        // use the constructor of TreeSet
        points = new TreeSet<>();

    }

    public boolean isEmpty() {
        // is the set empty?
        return points.isEmpty();
    }

    public int size() {
        // number of points in the set
        return points.size();
    }

    public void insert(Point2D p) {
        // add the point to the set (if it is not already in the set)
        checkNull(p);
        if (!points.contains(p)) {
            points.add(p);
        }
    }

    public boolean contains(Point2D p) {
        // does the set contain point p
        checkNull(p);
        return points.contains(p);
    }

    public void draw() {
        // draw all points to standard draw
        // TODO
    }

    public Iterable<Point2D> range(RectHV rect) {
        checkNull(rect);
        Point2D minPoint = new Point2D(rect.xmin(), rect.ymin());
        Point2D maxPoint = new Point2D(rect.xmax(), rect.ymax());
        List<Point2D> pointsInRect = new LinkedList<>(); // ???
        // The sub-set is inclusive for both extremes.
        // see doc of Oracle here instead of GeeksforGeeks for TreeSet subSet()
        for (Point2D p: points.subSet(minPoint, true, maxPoint, true)) {
            // The y-coordinate has been validated by the minPoint & maxPoint
            // see the doc of algs4.Point2D compareTo.
            if (p.x() >= rect.xmin() && p.x() <= rect.xmax()) {
                pointsInRect.add(p);
            }
        }
        return pointsInRect;
    }

    public Point2D nearest(Point2D p) {
        checkNull(p);
        if (this.isEmpty()) {
            return null;
        }

        // 1. Find the 2 neighbour points natural order, then find the 
        // closest distance d.
        // 2. Winden the navigatable set to a circle of d, save the nearest
        Point2D next = points.ceiling(p);
        Point2D prev = points.floor(p);
        if (next == null && prev == null) {
            return null;
        }
        double disNext = next == null ? Double.MAX_VALUE : p.distanceTo(next);
        double disPrev = prev == null ? Double.MAX_VALUE : p.distanceTo(prev);
        double d = Math.min(disNext, disPrev);

        Point2D minPoint = new Point2D(p.x(), p.y() - d);
        Point2D maxPoint = new Point2D(p.x(), p.y() + d);
        Point2D nearest = next == null ? prev : next; // cannot be both null;

        // The sub-set is inclusive for both extremities.
        for (Point2D candidate: points.subSet(minPoint, true, maxPoint, true)) {
            if (p.distanceTo(candidate) < p.distanceTo(nearest)) {
                nearest = candidate;
            }
        }
        return nearest;
    }

    // Helper function
    private void checkNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {
        // unit testing of the methods (optional)
    }
}