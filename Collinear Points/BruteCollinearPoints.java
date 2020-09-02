import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class BruteCollinearPoints {
   private final ArrayList<LineSegment> segments;   
   public BruteCollinearPoints(Point[] points) {    // finds all line segments containing 4 points
       if (points == null) throw new java.lang.IllegalArgumentException();
       segments = new ArrayList<LineSegment>();
       Point[] copy = new Point[points.length];
       for (int i = 0; i < points.length; i++) {
           if (points[i] == null) throw new java.lang.IllegalArgumentException();
           copy[i] = points[i];
       }
       Arrays.sort(copy);

       for (int i = 1; i < points.length; i++) {
           if (copy[i].compareTo(copy[i-1]) == 0) throw new java.lang.IllegalArgumentException();
       }
       
       for (int i = 0; i < copy.length - 3; i++) {
           Point p = copy[i];           
           for (int j = i+1; j < copy.length - 2; j++) {               
               for (int k = j+1; k < copy.length - 1; k++) {                  
                   if (copy[j].slopeTo(p) == copy[k].slopeTo(p)) {                       
                       for (int m = k+1; m < copy.length; m++) {                           
                           if (copy[m].slopeTo(p) == copy[k].slopeTo(p)) {
                               segments.add(new LineSegment(p, copy[m]));
                               //System.out.println(copy[l] +","+ copy[j]+","+ p);
                               //System.out.println(copy[l].slopeTo(p) +","+ copy[k].slopeTo(p));
                           }
                       }
                   } 
                }
            }
        
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
    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
        StdOut.println(segment);
        segment.draw();
    }
    StdDraw.show();
    }
}