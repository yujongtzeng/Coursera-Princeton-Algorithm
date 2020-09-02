import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdIn;
public class PercolationStats {
   private static final double PERCENTAGE = 1.96; 
   private double[] threshold;
   
   
   public PercolationStats(int n, int trials) {    // perform trials independent experiments on an n-by-n grid
       if (n <= 0 || trials <= 0) throw new java.lang.IllegalArgumentException();
       else {
           threshold = new double[trials];
           for (int i = 0; i < trials; i++) {
               Percolation site = new Percolation(n);
               int row = 0; 
               int col = 0;
               
               while (!site.percolates()) {
                   row = StdRandom.uniform(n)+1;
                   col = StdRandom.uniform(n)+1;
                   site.open(row, col);                  
               }
               threshold[i] = site.numberOfOpenSites()*1.0/(n*n);
           }
       }    
   }
   public double mean() {                          // sample mean of percolation threshold       
       return StdStats.mean(threshold);
   }                          
   public double stddev() {                        // sample standard deviation of percolation threshold
       return StdStats.stddev(threshold);
   }
   public double confidenceLo() {                  // low  endpoint of 95% confidence interval
       return (mean() - PERCENTAGE*stddev()/Math.sqrt(threshold.length));
   }
   public double confidenceHi() {                  // high endpoint of 95% confidence interval
       return (mean() + PERCENTAGE*stddev()/Math.sqrt(threshold.length));
   }
   public static void main(String[] args) {        // test client (described below)
       int n = StdIn.readInt();
       int trials = StdIn.readInt();
       /* try {
           n= Integer.parseInt(args[0]);
           trials= Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {System.out.println("wowo");} */
        while (StdIn.hasNextLine()) {
            n = StdIn.readInt();
            trials = StdIn.readInt();
            PercolationStats experiment = new PercolationStats(n, trials);
            System.out.println("mean                    = "+experiment.mean());
            System.out.println("stddev                  = "+experiment.stddev());
            System.out.println("95% confidence interval = ["+experiment.confidenceLo()+ ", " + experiment.confidenceHi() + "]");
        }  
   }    
}
