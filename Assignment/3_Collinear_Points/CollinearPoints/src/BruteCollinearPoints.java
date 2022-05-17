import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class BruteCollinearPoints {
    private final LineSegment[] segments;

    public BruteCollinearPoints(Point[] points)
    {
        // Check for exception
        if (points == null)
            throw new IllegalArgumentException();
        
        for (int i = 0; i < points.length; i++)
        {
            if (points[i] == null)
                throw new IllegalArgumentException();
        }
        
        for (int i = 0; i < points.length; i++)
        {
            for (int j = i+1; j < points.length; j++)
            {
                if (points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException();
            }
        }

        // Copy data
        Point[] pts = new Point[points.length];
        for (int i = 0; i < points.length; i++)
        {
            pts[i] = points[i];
        }

        // Sort Points
        Arrays.sort(pts);

        // for (int i = 0; i < points.length;i++)
        // {
        //     StdOut.print(points[i].toString() + " ");
        // }
        
        // Allocate
        LineSegment[] tempSs = new LineSegment[pts.length];

        // Count Segments
        int count = 0;
        for (int i = 0; i < pts.length; i++)
        {
            for (int j = i+1; j < pts.length; j++)
            {
                for (int k = j+1; k < pts.length; k++)
                {
                    for (int q = k+1; q < pts.length; q++)
                    {
                        if (Double.compare(pts[i].slopeTo(pts[j]), pts[i].slopeTo(pts[k])) == 0
                            && Double.compare(pts[i].slopeTo(pts[j]), pts[i].slopeTo(pts[q])) == 0)
                        {
                            tempSs[count++] = new LineSegment(pts[i], pts[q]);
                        }
                    }
                }
            }
        }

        segments = new LineSegment[count];
        for (int i = 0; i < count; i++)
            segments[i] = tempSs[i];
        
        // free
        tempSs = null;
    }

    public int numberOfSegments() { return segments.length; }

    public LineSegment[] segments()
    {
        LineSegment[] ans = new LineSegment[segments.length];
        for (int i = 0; i < segments.length; i++)
        {
            ans[i] = segments[i];
        }
        return ans; 
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
