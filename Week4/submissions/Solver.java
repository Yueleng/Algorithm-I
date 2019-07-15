import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import java.util.Deque;
import java.util.LinkedList;

// How to compile:
// javac -cp C:\Users\Yueleng\Coursera\algs4\algs4.jar Board.java Solver.java
// current folder: C:\Users\Yueleng\Coursera\algs4\Week4\submissions\


public class Solver {
    private boolean isSolvable; // return in method isSolvable
    private MinPQ<SearchNode> minPQ; // 
    private SearchNode solutionNode; // 

    private Board board;
    /**
     * find a solution to the initial board (using the A* algorithm)
     */
    public Solver(Board initial) {
        solutionNode = null;
        minPQ = new MinPQ<>();
        minPQ.insert(new SearchNode(initial, 0, null));

        while (true) {
            // del the root node and return from minPQ.
            SearchNode currNode = minPQ.delMin();
            Board currBoard = currNode.getBoard();
            
            // determine if goal is achieved or unsolvable in every loop.
            if (currBoard.isGoal()) {
                isSolvable = true;
                solutionNode = currNode;
                break;
            }

            // Q: ??? Not very clear here.   
            if (currBoard.hamming() == 2 && currBoard.twin().isGoal()) {
                isSolvable = false;
                break;
            }

            // Insert each neighbor except the board of the previous search node
            int moves = currNode.getMoves();
            Board prevBoard = moves > 0 ? currNode.prev().getBoard() : null;

            for (Board nextBoard : currBoard.neighbors()) {
                if (prevBoard != null && nextBoard.equals(prevBoard)) {
                    continue; // do not insert if 1. first node  or 2. prevBoard equals nextBoard.
                }
                minPQ.insert(new SearchNode(nextBoard, moves+1, currNode));
            }
        }
    }

    /**
     * is the initial board solvable?
     */
    public boolean isSolvable() {
        return isSolvable;
    }

    /**
     * min number of moves to solve initial board; -1 if unsolvable.
     */
    public int moves() {
        return isSolvable() ? solutionNode.getMoves() : -1;
    }

    /**
     * sequence of boards in a shortest solution; null if unsolvable.
     */
    public Iterable<Board> solution() {
        if (!isSolvable) {
            return null;
        }

        // Q: ??? Not very clear here
        Deque<Board> solution = new LinkedList<>();
        SearchNode node = solutionNode;
        while (node != null) {
            solution.addFirst(node.getBoard());
            node = node.prev();
        }
        return solution;
    }

    // inner class SearchNode which encapsulates Board class.
    private class SearchNode implements Comparable<SearchNode> {
        private final Board board;
        private final SearchNode prev;
        private final int moves;

        SearchNode(Board board, int moves, SearchNode prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
        }

        @Override
        public int compareTo(SearchNode that) {
            return this.priority() - that.priority();
        }

        public int priority() {
            return board.manhattan() + moves;
        }

        public Board getBoard() {
            return board;
        }

        public int getMoves() {
            return moves;
        }

        public SearchNode prev() {
            return prev;
        }
    }
    public static void main(String[] arg) {
        // solve a slider puzzle (given below)
        // create initial board from file
        
        
        // In in = new In(args[0]); // args[0] is the filename;
        // int n = in.readInt();
        // int[][] blocks = new int[n][n];
        // for (int i = 0; i < n; i++)
        //     for (int j = 0; j < n; j++)
        //         blocks[i][j] = in.readInt();
        // Board initial = new Board(blocks);

        // // solve the puzzle
        // Solver solver = new Solver(initial);

        // // print solution to standard output
        // if (!solver.isSolvable())
        //     StdOut.println("No solution possible");
        // else {
        //     StdOut.println("Minimum number of moves = " + solver.moves());
        //     for (Board board : solver.solution())
        //         StdOut.println(board);
        // }
    }
}