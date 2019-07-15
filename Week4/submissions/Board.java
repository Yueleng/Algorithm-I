import java.util.LinkedList;
import java.util.List;

// This data type should be immutable.
public class Board {
    
    private final int n;
    private final int[][] blocks;
    private int blankRow;
    private int blankCol;

    /**
     * Construct a board from n-by-n array of bloacks
     * (where blocks[i][j] = block in row i, column j)
     * @param blocks
     */
    public Board(int[][] blocks) {
        // construct a board from an n-by-n array of blocks

        if (blocks == null) {
            throw new NullPointerException();
        }
        // init
        // this.blocks = copyOf(blocks);
        n = blocks.length;
        for (int i = 0; i < n; i++) {
            if (n != blocks[i].length) {
                throw new NullPointerException();
            }
        }
        
        this.blocks = new int[n][n];
        blankRow = -1;
        blankCol = -1;

        // determine blank block
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                this.blocks[row][col] = blocks[row][col];
                if (this.blocks[row][col] == 0) {
                    blankRow = row;
                    blankCol = col;
                }
            }
        }
    }

    public int dimension() {
        // board dimension n
        return this.n;
    }

    /**
     * Number of blocks out of place, not counting blank block
     */
    public int hamming() {
        // number of blocks out of place
        int hamDis = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (row == blankRow && col == blankCol)
                    continue; // not counting blank block
                if (manhattan(row, col) > 0)
                    hamDis++;
            }
        }
        return hamDis;
    }

    /**
     * Sum of Manhattan distances between blocks and goal, not counting blank block.
     */
    public int manhattan() {
        int manDis = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (row == blankRow && col == blankCol)
                    continue; // not couting blankblock
                manDis += manhattan(row, col);
            }
        }
        return manDis;
    }

    public Board twin() {
        // a board that is obtained by exchanging any pair of blocks.
        int[][] clonedBlocks = new int[n][n]; 
        if (blankRow != 0) {
            // make sure blank block does not lie on row 0;
            swap(clonedBlocks, 0, 0, 0, 1);
        } else {
            // now blank block lie on row 0, we do switch on row 1;
            swap(clonedBlocks, 1, 0, 1, 1);
        }
        return new Board(clonedBlocks);
    }

    public boolean equals(Object y) {
        // does this board equal y?
        if (y == this) 
            // same instance.
            return true;
        if (y == null) 
            return false;
        if (this.getClass() != y.getClass())
            return false;
        
        Board that = (Board) y;
        if (this.blankRow != that.blankRow 
         || this.blankCol != that.blankCol
         || this.n != that.n)
            return false;
        for (int row = 0; row < n; row++)
            for (int col = 0; col < n; col++)
                if (this.blocks[row][col] != this.blocks[row][col])
                    return false;
        
        return true;
    }

    public Iterable<Board> neighbors() {
        // all neighboring boards
        List<Board> neighbors = new LinkedList<>();
        if (blankRow > 0) {
            int[][] north = copyOf(blocks);
            swap(north, blankRow, blankCol, blankRow-1, blankCol);
            neighbors.add(new Board(north));
        }

        if (blankRow < n-1) {
            int[][] south = copyOf(blocks);
            swap(south, blankRow, blankCol, blankRow+1, blankCol);
            neighbors.add(new Board(south));
        }

        if (blankCol > 0 ) {
            int[][] west = copyOf(blocks);
            swap(west, blankRow, blankCol, blankRow, blankCol-1);
            neighbors.add(new Board(west));
        }

        if (blankCol < n-1) {
            int[][] east = copyOf(blocks);
            swap(east, blankRow, blankCol, blankRow, blankCol+1);
            neighbors.add(new Board(east));
        }

        return neighbors;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(n).append("\n");
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                builder.append(String.format("%2d ", blocks[row][col]));
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    // helper function: calculate the manhattan value for a certain block;
    private int manhattan(int row, int col) {
        int value = blocks[row][col] - 1;
        int valueRow = value / n;
        int valueCol = value % n;
        return Math.abs(valueRow - row) + Math.abs(valueCol - col);
    }

    public boolean isGoal() {
        // is this board the goal board?
        return hamming() == 0;
    }

    /**
     * helper function: do swap on blocks (n-by-n array)
     * 1. Can swap two non-blank blocks, used in method twin().
     * 2. Can swap one non-blank block with a blank block, i.e., move, used in method neighbors().
     * Q: It is true that array is passed by pointer???
     */
    private void swap(int[][] blocks, int rowA, int colA, int rowB, int colB) {
        int swap = blocks[rowA][colA];
        blocks[rowA][colA] = blocks[rowB][colB];
        blocks[rowB][colB] = swap;
    }

    private int[][] copyOf(int[][] matrix) {
        int[][] clone = new int[matrix.length][];
        for (int row = 0; row < matrix.length; row++) {
            clone[row] = matrix[row].clone();
        }
        return clone;
    }

    public static void main(String[] args) {
        // unit tests (not graded)
    }
}