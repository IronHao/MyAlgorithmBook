import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class FastCollinearPoints {
    private final LineSegment[] segments;

    public FastCollinearPoints(Point[] points)     // finds all line segments containing 4 or more points
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

        // Copy
        Point[] pts = new Point[points.length];
        copyPts(points, pts);
        Point[] tempPts = new Point[points.length];
        copyPts(pts, tempPts);

        LineSegment[] tempSs = new LineSegment[points.length * points.length];
        int tempSegmentSize = 0;

        Arrays.sort(pts);
        Point[] otherPts = new Point[pts.length-1];
        for (int i = 0; i < pts.length; i++)
        {
            // Get the points except current one.
            for (int j = 0; j < pts.length; j++)
            {
                if (j < i)
                    otherPts[j] = pts[j];
                else if (j > i)
                    otherPts[j-1] = pts[j];
            } 
            // Sort the points according to the slopes makes with p
            Arrays.sort(otherPts, pts[i].slopeOrder());
            int count = 2;
            for (int j = 0; j < otherPts.length - 1; j++)
            {
                if (Double.compare(pts[i].slopeTo(otherPts[j]), pts[i].slopeTo(otherPts[j+1])) == 0)
                {
                    count++;
                    if (j == otherPts.length - 2 && count >= 4)
                        if (pts[i].compareTo(otherPts[j-count+3]) < 0)
                            tempSs[tempSegmentSize++] = new LineSegment(pts[i], otherPts[otherPts.length-1]);
                }
                else
                {
                    if (count >= 4 && pts[i].compareTo(otherPts[j-count+2]) < 0)
                        tempSs[tempSegmentSize++] = new LineSegment(pts[i], otherPts[j]);
                    count = 2;
                }
            }
        }

        segments = new LineSegment[tempSegmentSize];
        for (int i = 0; i < tempSegmentSize; i++)
            segments[i] = tempSs[i];
    }

    public int numberOfSegments() { return segments.length; }        // the number of line segments
    
    public LineSegment[] segments()                // the line segments
    {
        LineSegment[] ans = new LineSegment[segments.length];
        for (int i = 0; i < segments.length; i++)
        {
            ans[i] = segments[i];
        }
        return ans; 
    }

    private void copyPts(Point[] origin, Point[] newArr)
    {
        for (int i = 0; i < origin.length; i++)
        {
            if (i >= origin.length)
                throw new ArrayIndexOutOfBoundsException();
            newArr[i] = origin[i];
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
    
        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
 }