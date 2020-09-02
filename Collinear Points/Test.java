import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
public class Test
{
    public static void main(String[] args) {

    // read the n points from a file
    Point[] points = {new Point(10000,0), new Point(0,10000), new Point(3000,7000), new Point(7000,3000)};
    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    System.out.println(collinear.numberOfSegments() );
    for (LineSegment segment : collinear.segments()) {
        StdOut.println(segment);
        segment.draw();
    }
    StdDraw.show();
    }
}
