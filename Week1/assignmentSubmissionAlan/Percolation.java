import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private final WeightedQuickUnionUF sitesUF;
    private final WeightedQuickUnionUF sitesUFFull;
    private final int n;
    private boolean[][] sites;
    private int numberOfOpenSites;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        sitesUF = new WeightedQuickUnionUF(n * n + 2);
        sitesUFFull = new WeightedQuickUnionUF(n * n + 1); // introduced to serve method isFull
        sites = new boolean[n][n];
        numberOfOpenSites = 0;
        this.n = n;
    }   

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || row >= n + 1 || col <= 0 || col >= n + 1)
            throw new IllegalArgumentException();
        if (isOpen(row, col)) return;
        
        sites[row - 1][col - 1] = true;
        numberOfOpenSites++;
        
        // try to union upper site
        if (row == 1) {
            sitesUF.union(rowCol2Index(row, col), 0);
            sitesUFFull.union(rowCol2Index(row, col), 0);
        } else if (isOpen(row - 1, col)) {
            sitesUF.union(rowCol2Index(row, col), rowCol2Index(row - 1, col));
            sitesUFFull.union(rowCol2Index(row, col), rowCol2Index(row - 1, col));
        }
        
        // try to union lower site
        if (row == n) {
            sitesUF.union(rowCol2Index(row, col), n * n + 1);
        } else if (isOpen(row + 1, col)) {
            sitesUF.union(rowCol2Index(row, col), rowCol2Index(row + 1, col));
            sitesUFFull.union(rowCol2Index(row, col), rowCol2Index(row + 1, col));
        }

        // try to union left site
        if (col - 2 >= 0 && isOpen(row, col - 1)) {
            sitesUF.union(rowCol2Index(row, col), rowCol2Index(row, col - 1));
            sitesUFFull.union(rowCol2Index(row, col), rowCol2Index(row, col - 1));
        }

        // try to union right site
        if (col <= n - 1 && isOpen(row, col + 1)) {
            sitesUF.union(rowCol2Index(row, col), rowCol2Index(row, col + 1));
            sitesUFFull.union(rowCol2Index(row, col), rowCol2Index(row, col + 1));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || row >= n + 1 || col <= 0 || col >= n + 1)
            throw new IllegalArgumentException();
        return sites[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || row >= n + 1 || col <= 0 || col >= n + 1)
            throw new IllegalArgumentException();
        return sitesUFFull.connected(0, rowCol2Index(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return sitesUF.connected(0, n * n + 1);
    }

    // helper function
    private int rowCol2Index(int row, int col) {
        if (row <= 0 || row >= n + 1 || col <= 0 || col >= n + 1)
            throw new IllegalArgumentException();
        return n * (row - 1) + col;
    }

    // test client (optional)
    public static void main(String[] args) {
        // Client Code
    }
}