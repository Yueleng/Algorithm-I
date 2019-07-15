/**
 * URL: https://github.com/bearpaw/Algorithms-Part-I/blob/master/A1_Percolation/Percolation.java
 */
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n, numOpenSites;
    private WeightedQuickUnionUF uf;
    private boolean[] blocked;
    private boolean[] full;

    public Percolation(int num) {
        if (num <= 0) {
            throw new IllegalArgumentException();
        }

        // create n-by-n grid, with all sites blocked
        n = num;
        uf = new WeightedQuickUnionUF(n * n + 2);
        blocked = new boolean[n*n + 2]; // site 0 and n*n + 1 are the virtual sites.
        full = new boolean[n*n + 2];
        for (int i = 1; i <= n*n; i++) {
            blocked[i] = true;
            full[i] = false;
        }
        
        numOpenSites = 0;
    }

    private int index(int row, int col) {
        // return index of the site in the array
        if (row <= 0 || row > n || col <= 0 || col > n) {
            throw new IllegalArgumentException();
        }
        return (row-1) * n + col;
    }

    public void open(int row, int col) {
        // open site (row, col) if it is not open already
        int id = index(row, col);
        if (!isOpen(row, col)) {
            numOpenSites++;
            blocked[id] = false;
            // build connections
            if (row >= 2 && isOpen(row-1, col)) {
                uf.union(id, index(row-1, col));
            }

            if (row <= n-1 && isOpen(row+1, col)) {
                uf.union(id, index(row+1, col));
            }

            if (col >= 2 && isOpen(row, col-1)) {
                uf.union(id, index(row, col-1));
            }

            if (col <= n-1 && isOpen(row, col+1)) {
                uf.union(id, index(row, col+1));
            }

            if (row == 1) {
                uf.union(id, 0);
                full[id] = true;
            }

            if (row == n) {
                uf.union(id, n*n+1);
                if (full[id]) full[n*n + 1] = true; // very important
            }
        }
    }

    public boolean isOpen(int row, int col) {
        // is site (row, col) open?
        return !blocked[index(row, col)];
    }
    
    public boolean isFull(int row, int col) {
        int id = index(row, col);
        if (!full[id]) {
            full[id] = uf.connected(0, id);
        }
        return full[id];
    }

    public int numberOfOpenSites() {
        return numOpenSites;
    }

    public boolean percolates() {
        if (!full[n*n+1]) {
            full[n*n+1] = uf.connected(0, n*n+1);
        }
        return full[n*n+1];
    }

 
    // public static void main(String[] args) {

    // }

}