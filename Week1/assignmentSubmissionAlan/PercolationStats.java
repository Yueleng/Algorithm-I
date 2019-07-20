import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] trialResults;
    private final int numOfTrials;
    private final static double CONFIDENCE_95 = 1.96;
    private final double mean;
    private final double stddev;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        trialResults = new double[trials];
        numOfTrials = trials;
        int[] sitesIndex = new int[n * n];
        for (int j = 0; j < n * n; j++) {
            sitesIndex[j] = j;
        }
        int startIndex;
        int row, col;
        Percolation percolation;
        for (int i = 0; i < trials; i++) {
            percolation = new Percolation(n);
            StdRandom.shuffle(sitesIndex);
            startIndex = 0;
            row = 0;
            col = 0;
            while (!percolation.percolates()) {
                row = sitesIndex[startIndex] / n + 1;
                col = sitesIndex[startIndex] - (row - 1) * n + 1; 
                percolation.open(row, col);
                startIndex++;
            }
            trialResults[i] = (double) percolation.numberOfOpenSites() / (n * n);
        }
        mean = StdStats.mean(trialResults);
        stddev = StdStats.stddev(trialResults);
    }

    // sample mean of percolation threshold
    public double mean() {
        return this.mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return this.stddev;
        
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        double lowerBound = this.mean - CONFIDENCE_95 * this.stddev / Math.sqrt(numOfTrials);
        return lowerBound;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double upperBound = this.mean + CONFIDENCE_95 * this.stddev / Math.sqrt(numOfTrials);
        return upperBound;
    }

   // test client (see below)
   public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);
        System.out.printf("mean                    = %f\n", stats.mean());
        System.out.printf("stddev                  = %f\n", stats.stddev());
        System.out.printf("95%% confidence interval = [%f, %f]\n", stats.confidenceLo(), stats.confidenceHi());
   }

}