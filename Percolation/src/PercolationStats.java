import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double [] threshold;
    private int T;
    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials){
        this.T = trials;
        if(n <= 0 || trials <= 0){
            throw new java.lang.IllegalArgumentException("invalid argument");
        }
        threshold = new double[trials];
        for(int i=0; i < trials; i++){
            Percolation pc = new Percolation(n);
            while(pc.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                pc.open(row, col);
            }
            int count = pc.numberOfOpenSites();
            threshold[i] = count/n;
        }

    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(threshold);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(threshold);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo(){
        double mean = StdStats.mean(threshold);
        double stddev = StdStats.stddev(threshold);
        return mean - 1.96 * stddev / Math.sqrt(T);
    }


    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        double mean = StdStats.mean(threshold);
        double stddev = StdStats.stddev(threshold);
        return mean + 1.96 * stddev / Math.sqrt(T);
    }
}