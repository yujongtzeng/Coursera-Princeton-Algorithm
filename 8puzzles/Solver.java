import java.util.LinkedList;
import java.util.Deque;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
public class Solver {    
    private final Board ini;    
    private Node lastNode;
    private boolean isSolvable;    
    private class Node implements Comparable<Node> {
        private final Board board;
        private Node prev;
        private final int moves;
        private final int priority;
        private final boolean isSelf;
        private Node(Board initial, boolean isSelf, int moves) {
            board = initial;
            prev = null;
            this.moves = moves;
            this.isSelf = isSelf;
            priority = moves + board.manhattan();
        }
        
        public int compareTo(Node that) {            
            if (this.priority > that.priority) return 1;
            if (this.priority < that.priority) return -1;
            else return 0;
        }   
    }
      
    public Solver(Board initial) {          // find a solution to the initial board (using the A* algorithm)
        if (initial == null) throw new java.lang.IllegalArgumentException();
        ini = initial;         
        lastNode = null;  
        solve();
    }
    private void solve() {                   
        Node cur = new Node(ini, true, 0);
        MinPQ<Node> gamePQ = new MinPQ<>();
        Node twin = new Node(ini.twin(), false, 0);
        gamePQ.insert(twin);        
        while (!cur.board.isGoal()) {                                       
            for (Board b: cur.board.neighbors()) {
                if (cur.prev == null || !b.equals(cur.prev.board)) {
                    Node newNode = new Node(b, cur.isSelf, cur.moves+1);                    
                    newNode.prev = cur;
                    gamePQ.insert(newNode);
                }
            }                            
            cur = gamePQ.delMin();
        }              
        lastNode = cur;
        isSolvable = cur.isSelf;
    }
    public boolean isSolvable() {           // is the initial board solvable?        
        return isSolvable;
    }
    public int moves() {                    // min number of moves to solve initial board; -1 if unsolvable
        if (!isSolvable) return -1;       
        return lastNode.moves;
    }
    public Iterable<Board> solution() {     // sequence of boards in a shortest solution; null if unsolvable
        if (!isSolvable) return null;        
        Deque<Board> path = new LinkedList<>();
        Node current = lastNode;
        while (current != null) {
            path.push(current.board);
            current = current.prev;
        }    
        return path;
    }
    public static void main(String[] args) {  // solve a slider puzzle (given below)           
        // create initial board from file
        
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
        for (Board board : solver.solution())
            StdOut.println(board);       
        }
          
        /*
        int[][] blocks = {{2,3},{1,0}};
        Solver test = new Solver(new Board(blocks));
        
        System.out.println("Is the board solvable? " + test.isSolvable());
        System.out.println("Least moves " + test.moves());
        for (Board b : test.solution()) {
            System.out.println(b.toString());
        } */
        
    }
}