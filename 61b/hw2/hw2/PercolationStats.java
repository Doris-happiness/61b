package hw2;

import edu.princeton.cs.introcs.StdStats;
import edu.princeton.cs.introcs.StdRandom;


public class PercolationStats {
    private int n; // represent the grid of n
    private int t; // represent the repeat times
    private PercolationFactory perPf;
    private Percolation[] experiments; // the array stored t Percolation
    private double[] results;
    private StdRandom randomHelper;

    /** perform T independent experiments on an N-by-N grid. */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T should both be positive.");
        }
        this.n = N;
        this.t = T;
        this.perPf = pf;
        results = new double[t];
        experiments = new Percolation[T];
        for (int i = 0; i < t; i += 1) {
            experiments[i] = pf.make(n);
        }
    }

    /** Monte Carlo simulation. */
    public void setExperiments() {
        for (Percolation expe : experiments) {
            int cnt = 0;
            while (!expe.percolates()) {
                int indexRow = StdRandom.uniform(0, n);
                int indexCol = StdRandom.uniform(0, n);
                if (expe.isOpen(indexRow, indexCol)) {
                    continue;
                } else {
                    expe.open(indexRow, indexRow);
                }
            }
            results[cnt] = expe.numberOfOpenSites() / (n * n);
            cnt += 1;
        }
    }


    /** sample mean of percolation threshold. */
    public double mean() {
        return StdStats.mean(results);
    }

    /** sample standard deviation of percolation threshold. */
    public double stddev() {
        return StdStats.stddev(results);
    }

    /** low endpoint of 95% confidence interval. */
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(t);

    }

    /** high endpoint of 95% confidence interval. */
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(t);
    }


}
