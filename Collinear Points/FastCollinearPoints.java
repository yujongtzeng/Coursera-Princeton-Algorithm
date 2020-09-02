import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class FastCollinearPoints {
   private final ArrayList<LineSegment> segments;
  
   public FastCollinearPoints(Point[] points) {     // finds all line segments containing 4 or more points
       if (points == null) throw new java.lang.IllegalArgumentException();
       segments = new ArrayList<LineSegment>();
       Point[] copy = new Point[points.length];
       for (int i = 0; i < points.length; i++) {
           if (points[i] == null) throw new java.lang.IllegalArgumentException();
           copy[i] = points[i];
       }
                     
       for (int i = 0; i < points.length; i++) {
           Point p = points[i];
           Arrays.sort(copy, p.slopeOrder());
           if (i == 0) {
               for (int j = 1; j < points.length; j++) {
                   if (copy[j].compareTo(copy[j-1]) == 0) throw new java.lang.IllegalArgumentException();
               }   
           }
           int farest = 1;
           boolean add = true;
           int count = 1;
           for (int j = 1; j < points.length; j++) {
               if (copy[j].slopeTo(p) == copy[farest].slopeTo(p)) {
                   count++;
                   if (copy[j].compareTo(p) < 0) add = false;
                   else if (copy[farest].compareTo(copy[j]) < 0) {
                       farest = j;
                   }
               }
               else {
                   if (add && count >= 4) segments.add(new LineSegment(p, copy[farest]));
                   farest = j;
                   count = 2;
                   if (copy[j].compareTo(p) < 0) add = false;
                   else add = true;
                }               
           }
           if (add && count >= 4) segments.add(new LineSegment(p, copy[farest])); 
       }
       
       
       
   }
   public           int numberOfSegments() {        // the number of line segments
       return segments.size();
   }
   public LineSegment[] segments() {               // the line segments
       return segments.toArray(new LineSegment[segments.size()]);
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

    // print and draw the line segments
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
        StdOut.println(segment);
        segment.draw();
    }
    StdDraw.show();
}
}