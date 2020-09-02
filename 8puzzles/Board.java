import java.util.LinkedList;
public class Board {
    private int[][] blocks;
    private final int n;
    private final int hamming;
    private final int manhattan;    
    public Board(int[][] blocks) {          // construct a board from an n-by-n array of blocks
        this.blocks = copy(blocks);
        n = blocks.length;
        
        int count = -1;       // pre-compute hamming             
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (blocks[i][j] != index(i, j) +1) {
                    count++;
                }    
            }
        }        
        hamming = count;         
        
        count = 0;      // pre-compute manhattan
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (blocks[i][j] != index(i, j) +1 && blocks[i][j] != 0) {
                    int row = (blocks[i][j] -1)/n;
                    int col = (blocks[i][j] -1) % n;
                    count = count + Math.abs(i - row) + Math.abs(j - col);
                }
            }
        }        
        manhattan = count;
    }                                       // (where blocks[i][j] = block in row i, column j)
    public int dimension() {                // board dimension n
        return n;
    }
    public int hamming() {                  // number of blocks out of place
        return hamming;
    }
    public int manhattan() {               // sum of Manhattan distances between blocks and goal
        return manhattan;
    }
    public boolean isGoal() {               // is this board the goal board?
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (blocks[i][j] != index(i, j) +1 &&  (i != n-1 || j != n-1)) {
                    return false;
                }    
            }
        }
        return true;
    }
    public Board twin() {                   // a board that is obtained by exchanging any pair of blocks
        if (blocks[0][0] != 0 && blocks[0][1] != 0) {
            exch(0, 0, 0, 1);
            Board twin = new Board(blocks);
            exch(0, 0, 0, 1);
            return twin;
        }
        exch(n-1, n-1, n-1, n-2);
        Board twin = new Board(blocks);
        exch(n-1, n-1, n-1, n-2);
        return twin;
    }
    public boolean equals(Object y) {       // does this board equal y?
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        if (dimension() != this.dimension()) return false;
        if (blocks[0].length != that.blocks[0].length) return false;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (blocks[i][j] != that.blocks[i][j]) {
                    return false;
                }    
            }
        }
        return true;
    }
    public Iterable<Board> neighbors() {    // all neighboring boards
        int i = 0;
        int j = 0;
        while (blocks[i][j] != 0) {     //(i, j) is the position of 0
            j++;
            if (j == n) {
                j = 0;
                i++;
            }
        }
        LinkedList<Board> neigh = new LinkedList<Board>();
        addNb(i, j, i-1, j, neigh);
        addNb(i, j, i+1, j, neigh);
        addNb(i, j, i, j-1, neigh);
        addNb(i, j, i, j+1, neigh);
        return neigh;
    }
    private void addNb(int i, int j, int a, int b, LinkedList<Board> neigh) {
        if (a < 0 || a >= n) return;
        if (b < 0 || b >= n) return;
        
        exch(i, j, a, b);
        Board nb = new Board(blocks);
        neigh.add(nb);
        exch(i, j, a, b);
    }
    public String toString() {              // string representation of this board (in the output format specified below)
        StringBuilder str = new StringBuilder();
        str.append(dimension() + "\n");
        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks.length; col++)
                str.append(String.format("%2d ", blocks[row][col]));
            str.append("\n");
        }
        return str.toString();    
    }
    private int index(int i, int j) {
        return i*n + j;
    }
   
    private void exch(int i, int j, int a, int b) {
        int temp = blocks[i][j];
        blocks[i][j] = blocks[a][b];
        blocks[a][b] = temp;
    }
    
    private int[][] copy(int[][] m) {
        int[][] copy = new int[m.length][m[0].length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                copy[i][j] = m[i][j];
            }
        }
        return copy;
    }

    public static void main(String[] args) { // unit tests (not graded)
        /*
        int[][] blocks = {{1, 0}, {3, 2}};
        Board test = new Board(blocks);
        System.out.println(test.toString());
        */
    }    
}
