import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
   private boolean[][] openess;  
   private boolean[] connTop;   
   private boolean[] connBottom;   
   private int countOfOpen;
   private int n;
   private WeightedQuickUnionUF grids;
   private boolean percolates;
   
    public Percolation(int n) {              // create n-by-n grid, with all sites blocked         
       if (n <= 0) throw new java.lang.IllegalArgumentException();
       else {
          grids = new WeightedQuickUnionUF(n*n+1); 
          openess = new boolean[n][n];
          connTop = new boolean[n*n]; 
          connBottom = new boolean[n*n]; 
          countOfOpen = 0;
          this.n = n;
          percolates = false;
       }                  
   }                
   
   public  void open(int row, int col) {    // open site (row, col) if it is not open already
       if (row < 1 || col < 1 || row > n || col > n) throw new java.lang.IllegalArgumentException();
       else if (!openess[row-1][col-1]) {
           openess[row-1][col-1] = true;
           countOfOpen++;
           int q = grids.find(index(row, col));
           if (row == 1) {              
               connTop[q] = true;       
            } 
           if (row == n) {
               connBottom[q] = true;
            }  
           if (row-2 >= 0  && openess[row-2][col-1]) {               
               connect(row, col, row-1, col);
            }
           if (col-2 >= 0  && openess[row-1][col-2]) {                
               connect(row, col, row, col-1);
            }
           if (row <= n-1 && openess[row][col-1]) {               
               connect(row, col, row+1, col);
            }
           if (col <= n-1 && openess[row-1][col]) {               
               connect(row, col, row, col+1);
            }  
           q = grids.find(index(row, col));
           if (!percolates && connTop[q] && connBottom[q])  {
               percolates = true;
            }  
       }    
   }    
   public boolean isOpen(int row, int col) {  // is site (row, col) open?
       if (row < 1 || col < 1 || row > n || col > n) throw new java.lang.IllegalArgumentException();
       else return openess[row-1][col-1];
   }    
   public boolean isFull(int row, int col) {  // is site (row, col) full?
       if (row < 1 || col < 1 || row > n || col > n) throw new java.lang.IllegalArgumentException(); 
       else {
           // System.out.println(grids.find(index(row, col)));
           return connTop[grids.find(index(row, col))];         
       }    
   }
   public int numberOfOpenSites() {       // number of open sites
       return countOfOpen;
   }    
   public boolean percolates() {              // does the system percolate?
       return percolates;
   }
   private int index(int row, int col) {             
       return (row-1)*n+col-1;
   }
   private void connect(int row1, int col1, int row2, int col2) {
       int p = grids.find(index(row1, col1)); 
       int q = grids.find(index(row2, col2));       
       /* connTop[q] = connTop[p] || connTop[q];
       connTop[p] = connTop[q];
       connBottom[q] = connBottom[p] || connBottom[q];
       connBottom[p] = connBottom[q]; */       
       grids.union(index(row1, col1), index(row2, col2));    
       int r = grids.find(index(row1, col1)); 
       connTop[r] = connTop[p] | connTop[q];
       connBottom[r] = connBottom[p] | connBottom[q];
    }
   public static void main(String[] args) {   // test client (optional)
   } 
}