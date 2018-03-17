import java.lang.Math;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;



public class PercolationStats {
    private int numTrials;
    private double samMean;
    private double samStddev;
    private int[] openSites;
    private Percolation bob;


    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        this.numTrials = trials;
        this.samMean = 0;
        this.samStddev = 0;
        this.openSites = new int[n];

        for (int i = 0; i < trials; i++) {
            this.bob = new Percolation(n);
            simulation(n, i);
        }

    }

    // sample mean of percolation threshold
    public double mean() {
         return StdStats.mean(this.openSites);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.openSites);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(this.numTrials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(this.numTrials));
    }

    private void simulation(int n, int count) {
        while (!this.bob.percolates())
            this.bob.open(StdRandom.uniform(n), StdRandom.uniform(n));
        this.openSites[count] = bob.numberOfOpenSites();
        return;
    }

    // test client (see below)
    public static void main(String[] args) {

    }
}