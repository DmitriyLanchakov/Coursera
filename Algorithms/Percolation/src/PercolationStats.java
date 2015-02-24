/**
 * Created by den on 25.01.15.
 */
public class PercolationStats {
    private double[] fractions;
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) throw new IllegalArgumentException("N <=0 or T <=0 ");
        else {
            fractions = new double[T];
            int opened, i, j;
            Percolation p;
            for (int k = 0; k < T; k++) {
                opened = 0;
                p = new Percolation(N);
                while (!p.percolates())
                {
                    i = StdRandom.uniform(1, N+1);
                    j = StdRandom.uniform(1, N+1);
                    if (!p.isOpen(i, j)) {
                        p.open(i, j);
                        opened++;
                    }
                }
                fractions[k] = ((double) opened) / (N*N);
            }
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(fractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(fractions);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(fractions.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(fractions.length);
    }


    // test client (described below)
    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(20, 10);
        System.out.println(ps.confidenceLo());
        //System.out.println(ps.stddev());

    }
}