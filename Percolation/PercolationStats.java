public class PercolationStats {
    private static double ZCONSTANT = 1.96;
    private double[] results;
    private int x;
    private int y;
    private int openCount;
    
    // perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T) {    
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        results = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation test = new Percolation(N);
            openCount = 0;
             while (!test.percolates()) {
                 x = StdRandom.uniform(1 , N+1);
                 y = StdRandom.uniform(1 , N+1);
                 if (!test.isOpen(x , y)) {
                     test.open(x , y);
                     openCount++;
                 }
             }
             results[i] = (double) openCount/(N*N);
        }     
    }
    
    // sample mean of percolation threshold
    public double mean() {                     
        return StdStats.mean(results);
    }
    
    // sample standard deviation of percolation threshold
    public double stddev() {                  
        if (results.length == 1) {
            return Double.NaN;
        }
        return StdStats.stddev(results);
    }
    
    // returns lower bound of the 95% confidence interval
    public double confidenceLo() {          
        return mean()-((ZCONSTANT*stddev())/Math.sqrt((double) results.length));
    }
    
    // returns upper bound of the 95% confidence interval
    public double confidenceHi() {             
        return mean()+((ZCONSTANT*stddev())/Math.sqrt((double) results.length));
    }
    
    // test client, described below
    public static void main(String[] args) { 
        int N = StdIn.readInt();
        int T = StdIn.readInt();
        PercolationStats test = new PercolationStats(N , T);
        StdOut.println("Mean = " + test.mean() + "\n");
        StdOut.println("Stddev = " + test.stddev() + "\n");
        StdOut.println("95% confidence interval = " + test.confidenceLo() + "," 
                           + test.confidenceHi() + "\n");    
    }
}