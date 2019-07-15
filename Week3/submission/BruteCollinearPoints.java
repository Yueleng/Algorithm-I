import java.util.ArrayList;
import java.util.Arrays;
/**
 * Write a program BruteCollinearPoints.java that examines 4 points at a time 
 * and checks whether they all lie on the same line segment, returning all su
 * -ch line segments. To check whether the 4 points p, q, r, s are collinear,
 * check whether the three slopes between p and q, between p and r between p 
 * and s are equal.
 * 
 * Performance Requirement: The order of growth of the running time of your 
 * program should be n^4 in the worst case and it should use space proporti
 * -onal to n plus the number of line segments returned.
 */

public class BruteCollinearPoints {
    private double eps = 0.00000001;
    private ArrayList<LineSegment> lineSeg = new ArrayList<>();

    public BruteCollinearPoints(Point[] points) {
        
        /**
         * Corner cases: Throw a java.lang.IllegalArugment Exception 
         * if the argument to the constructor is null, 
         * if any point in the array is null, or
         * if the argument to the constructor contains a repeated point.
         */
        checkpoints(points);

        // Copy points for judge system
        Point[] pointsCopy = Arrays.copyOf(points, points.length);

        /**
         * The method segments() should include each line segment containing 4 points 
         * exactly once. If 4 points appear on a line segment in the order p -> q -> 
         * r -> s, then you should include either the line segment p -> s or s -> p (
         * but not both) and you should not include subsegments such as p -> r or q 
         * -> r. For simplicity, we will not supply any input to BruteCollinearPoints
         * that has 5 or more collinear points.
         */

        // sort points to ensure the every LineSegment stored in lineSeg consists of 
        // two endpoints.
        Arrays.sort(pointsCopy);

        double slope1, slope2, slope3;
        for (int p = 0; p < pointsCopy.length - 3; ++p)
            for (int q = p + 1; q < pointsCopy.length - 2; ++q)
                for (int r = q + 1; r < pointsCopy.length -1; ++r)
                    for (int s = r + 1; s < pointsCopy.length; ++s) {
                        slope1 = pointsCopy[p].slopeTo(pointsCopy[q]);
                        slope2 = pointsCopy[p].slopeTo(pointsCopy[r]);
                        slope3 = pointsCopy[p].slopeTo(pointsCopy[s]);

                        if (Math.abs(slope1 - slope2) < eps && Math.abs(slope1 - slope3) < eps) {
                            lineSeg.add(new LineSegment(pointsCopy[p], pointsCopy[s]));
                        } else if (slope1 == Double.POSITIVE_INFINITY && slope2 == Double.POSITIVE_INFINITY
                                    && slope3 == Double.POSITIVE_INFINITY) {
                            lineSeg.add(new LineSegment(pointsCopy[p], pointsCopy[s]));
                        }
                    }
    }

    public int numberOfSegments() {
        return lineSeg.size();
    }

    public LineSegment[] segments() {
        return lineSeg.toArray(new LineSegment[lineSeg.size()]);
    }


    // helper method(s)
    private void checkpoints(Point[] points) {
        // check argument is null
        if (points == null) throw new IllegalArgumentException("The array \"Points\" is null");
        
        // check any point in the array is null
        for (Point p : points) {
            if (p == null) {
                throw new NullPointerException("The array \"Points\" contains null element.");
            }
        }

        // check duplicates
        for (int i = 0; i < points.length - 1; ++i) 
            for (int j = i + 1; j < points.length; ++j) {
                if (points[i].compareTo(points[j]) == 0) 
                    throw new IllegalArgumentException("Duplicate(s) found.");
            }
    }

    public static void main(String args[]) {
        // unit test
    }
}