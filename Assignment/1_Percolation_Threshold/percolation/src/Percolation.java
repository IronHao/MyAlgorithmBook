import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation 
{
    private final WeightedQuickUnionUF map;    
    private boolean[] isOpen;           // True if is open
    private final int maxN;
    private int openNum;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n)
    {
        // Throw exception
        if (n < 0)
        {
            throw new IllegalArgumentException();
        }

        // Create and Init
        map = new WeightedQuickUnionUF(n*n + 2);    // n^2 index used to define the start point, n^2+1 index used to define the end point.
        for (int i = 0; i < n; i++)
        {
            map.union(i, n * n);
            map.union(i + n*(n-1), n*n + 1);
        }
        
        isOpen = new boolean[n*n];
        maxN = n;
        openNum = 0;

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                isOpen[i*n + j] = false;
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col)
    {
        // Throw exception
        if (row <= 0 || row > maxN || col <= 0 || col > maxN)
        {
            throw new IllegalArgumentException();
        }

        // Open
        if (!isOpen[(row-1) * maxN + col - 1])
        {
            isOpen[(row-1) * maxN + col - 1] = true;
            openNum++;
            // Connect it to all of its adjacent open sites
            if (row - 1 > 0 && row - 1 <= maxN)
            {
                if (isOpen(row - 1, col))
                    map.union((row-2) * maxN + col - 1, (row-1) * maxN + col - 1);
            }
        
            if (row + 1 > 0 && row + 1 <= maxN)
            {
                if (isOpen(row + 1, col))
                    map.union(row * maxN + col - 1, (row-1) * maxN + col - 1);
            }
            
            if (col - 1 > 0 && col - 1 <= maxN)
            {
                if (isOpen(row, col - 1))
                    map.union((row-1) * maxN + col - 2, (row-1) * maxN + col - 1);
            }
            
            if (col + 1 > 0 && col + 1 <= maxN)
            {
                if (isOpen(row, col + 1))
                    map.union((row-1) * maxN + col, (row-1) * maxN + col - 1);
            }
            
        }
            
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col)
    {
        // Throw exception
        if (row <= 0 || row > maxN || col <= 0 || col > maxN)
        {
            throw new IllegalArgumentException();
        }

        return isOpen[(row-1) * maxN + col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col)
    {
        // Throw exception
        if (row <= 0 || row > maxN || col <= 0 || col > maxN)
        {
            throw new IllegalArgumentException();
        }
        
        return ((isOpen(row, col)) && (map.find(maxN*maxN) == map.find((row-1) * maxN + col - 1)));
    }

    // returns the number of open sites
    public int numberOfOpenSites()
    {
        return openNum;
    }

    // does the system percolate?
    public boolean percolates()
    {
        return (map.find(maxN*maxN) == map.find(maxN*maxN + 1));
    }

    // test client (optional)
    public static void main(String[] args)
    {
        Percolation p = new Percolation(3);
        p.open(1, 1);
        StdOut.println(p.isFull(1, 1));
        StdOut.println(p.percolates());
        p.open(2, 1);
        p.open(3, 1);
        StdOut.println(p.numberOfOpenSites());
        StdOut.println(p.percolates());
    }
}