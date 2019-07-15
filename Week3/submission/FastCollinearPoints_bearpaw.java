import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

/**
 * A faster, sorting-based solution.
 * Principles: Given a point p, the following method determines whether p participate
 * in a set of 4 or more collinear points.
 * 1. Think of p as the orgin.
 * 2. For each other point q, determine the slope it makes with p.
 * 3. Sort the points according to the slopes they make with p.
 * 4. Check if any 3 (or more) adjacent points in the sorted order have equal slopes 
 *      w.r.t p. If so, these points together with p, are colliear
 * 
 * Performance requirement: The order of growth of the running time of your program 
 * should be n^2 * logn in the worst case and it should use space proportional to n 
 * plus the number of line segments returned. FastCollinearPoints should work prope
 * -rly even if the input has 5 or more collinear points.
 */

public class FastCollinearPoints {
    private double epsilon = 0.000001;
    private ArrayList<LineSegment> lineSeg = new ArrayList<>();
    private HashMap<Double, ArrayList<Point>> addedLineSeg = new HashMap<>();
    
    public FastCollinearPoints(Point[] points) {
        /**
         * Corner cases: Throw a java.lang.IllegalArugment Exception 
         * if the argument to the constructor is null, 
         * if any point in the array is null, or
         * if the argument to the constructor contains a repeated point.
         */
        checkpoints(points);

        Point[] pointsCopy = Arrays.copyOf(points, points.length);

        /**
         * The method segments() should include each maximal line segment containing 4 
         * (or more) points exactly once. For example, if 5 points appear on a line se
         * -gment in the order p -> q -> r -> s ->t, then do not include the subsegments 
         * p -> s or q -> t.
         */
        if (points.length >= 4) {
            // Q: why can we use points.length-3 instead of 
            // points.length here?
            for (int p = 0; p < points.length-3; ++p) { 
                // compute and sort slopes
                // now pointsCopy is a sorted array with pointsCopy[0] = p;
                Arrays.sort(pointsCopy, points[p].slopeOrder()); 

                double oldSlope = Double.NEGATIVE_INFINITY; // itself
                double curSlope = Double.NEGATIVE_INFINITY;
                ArrayList<Point> lineSegPoints = new ArrayList<>(); // temp stored points.
                lineSegPoints.add(points[p]);
                int firstSameSlopePtr = 1;
                for (int q = 1; q < pointsCopy.length; ++q) {
                    // q starts from 1 since pointsCopy[0] == points[p]
                    curSlope = points[p].slopeTo(pointsCopy[q]);
                    if (curSlope == oldSlope || Math.abs(curSlope - oldSlope) < epsilon) 
                        lineSegPoints.add(pointsCopy[q]);
                    else {
                        // check if we can add a new segment
                        lineSegPoints.add(pointsCopy[firstSameSlopePtr]);
                        if (lineSegPoints.size() >= 4) {
                            createLineSeg(oldSlope, lineSegPoints);
                        }
                        // clear state
                        lineSegPoints.clear();
                        lineSegPoints.add(points[p]);
                        firstSameSlopePtr = q;
                    }

                    oldSlope = curSlope;
                }
                lineSegPoints.add(pointsCopy[firstSameSlopePtr]);
                if (lineSegPoints.size() >=4)
                    createLineSeg(oldSlope, lineSegPoints);
            }
        }

    }

    public int numberOfSegments() {
        return lineSeg.size();
    }

    public LineSegment[] segments() {
        // toArray copies content into other array.
        return lineSeg.toArray(new LineSegment[lineSeg.size()]);
    }

    private boolean createLineSeg(double slope, ArrayList<Point> lineSegPoints) {
        // get already added points for this slope.
        ArrayList<Point> addedPoints = addedLineSeg.get(slope);

        // sort line segment points to eliminate sub-linesegs.
        Collections.sort(lineSegPoints);
        Point a = lineSegPoints.get(0);
        Point b = lineSegPoints.get(lineSegPoints.size() - 1);

        // add if not exists
        if (addedPoints == null) {
            lineSeg.add(new LineSegment(a, b));
            // save to already added linesegs.
            addedPoints = new ArrayList<>();
            addedPoints.add(b);
            addedLineSeg.put(slope, addedPoints);
        } else {
            // TODO: REVIEW CODE TO DETERMINE IF THIS IS NEEDED.
            for (Point p: addedPoints) {
                if (p.compareTo(b) == 0) {
                    return false;
                }
            }
            
            lineSeg.add(new LineSegment(a, b));
            // save to already added linesegs
            addedPoints.add(b);
            addedLineSeg.put(slope, addedPoints);
        }
        
        return true;
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

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // pinrt and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}