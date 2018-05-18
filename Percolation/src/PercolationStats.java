import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import java.lang.Integer;

public class PercolationStats{
    private final double [] threshold;
    private final int T;
    private final double CONFIDENCE_95 = 1.96;
    private final double mean;
    private final double stddev;
    //perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials){
        this.T = trials;
        if(n <= 0 || trials <= 0){
            throw new java.lang.IllegalArgumentException("invalid argument");
        }
        threshold = new double[trials];
        for(int i = 0; i < trials; i++){
            Percolation pc = new Percolation(n);
            while(!pc.percolates()){
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                pc.open(row, col);
            }

            int count = pc.numberOfOpenSites();
            Integer ct = count;
            Integer grid = n * n;
            threshold[i] = ct.doubleValue()/grid.doubleValue();
        }
        this.mean = StdStats.mean(threshold);
        this.stddev = StdStats.stddev(threshold);

    }

    //sample mean of percolation threshold
    public double mean(){
        return mean;
    }

    //sample standard deviation of percolation threshold
    public double stddev(){
        return stddev;
    }

    //low  endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean - CONFIDENCE_95 * stddev / Math.sqrt(T);
    }


    //high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean + CONFIDENCE_95 * stddev / Math.sqrt(T);
    }

    //test client
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        System.out.println(n);
        System.out.println(t);

        PercolationStats ps = new PercolationStats(n,t);
        System.out.println("mean                    = " + ps.mean());
        System.out.println("stddev                  = " + ps.stddev());
        System.out.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
    }
}
