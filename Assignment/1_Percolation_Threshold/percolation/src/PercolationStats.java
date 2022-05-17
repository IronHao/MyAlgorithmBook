import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats 
{
    private final int maxTrials;
    private final int maxN;
    private double[] percolateThreshold;

    private double meanVal;
    private double stddevVal;

    private static final double CONFIDENCE_95 = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials)
    {
        if (n <= 0 || trials <= 0)
        {
            throw new IllegalArgumentException();
        }

        maxTrials = trials;
        maxN = n;
        percolateThreshold = new double[trials];

        meanVal = 0;
        stddevVal = 0;
    }

    // sample mean of percolation threshold
    public double mean()
    {
        if (meanVal != 0)
            return meanVal;

        if (percolateThreshold[0] == 0)
            doMonteCarloSimulation();
        meanVal = StdStats.mean(percolateThreshold);
        return meanVal;
    }

    // sample standard deviation of percolation threshold
    public double stddev()
    {
        if (stddevVal != 0)
            return stddevVal;

        if (percolateThreshold[0] == 0)
            doMonteCarloSimulation();
        stddevVal = StdStats.stddev(percolateThreshold);
        return stddevVal;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo()
    {
        return mean() - (CONFIDENCE_95 * stddev() / Math.sqrt(maxTrials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {
        return mean() + (CONFIDENCE_95 * stddev() / Math.sqrt(maxTrials));
    }


    private void doMonteCarloSimulation()
    {
        int time = 0;
        while (time < maxTrials)
        {
            Percolation percolation = new Percolation(maxN);
            while (!percolation.percolates())
            {
                int row, col;
                do
                {
                    row = StdRandom.uniform(1, maxN+1);
                    col = StdRandom.uniform(1, maxN+1);
                }
                while (percolation.isOpen(row, col));
                
                percolation.open(row, col);
            }
            percolateThreshold[time] = Double.valueOf(percolation.numberOfOpenSites()) / (maxN*maxN);
            time++;
        }
    }

   // test client (see below)
   public static void main(String[] args)
   {
       PercolationStats p = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
       StdOut.printf("mean\t\t\t = %f\n", p.mean());
       StdOut.printf("stddev\t\t\t = %f\n", p.stddev());
       StdOut.print("95% confidence interval\t = [");
       StdOut.print(p.confidenceLo());
       StdOut.print(", ");
       StdOut.print(p.confidenceHi());
       StdOut.print("]\n");
   }

}
