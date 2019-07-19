import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private WeightedQuickUnionUF sitesUF;
    private int[][] sites;
    private int numberOfOpenSites;
    private int n;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        sitesUF = new WeightedQuickUnionUF(n * n + 2);
        sites = new int[n][n];
        numberOfOpenSites = 0;
        this.n = n;
    }   

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col)) return;
        
        sites[row - 1][col - 1] = 1;
        numberOfOpenSites++;
        if (row == 1) {
            sitesUF.union(n * (row - 1) + col, 0);
        } else if (isOpen(row - 1, col)) {
            sitesUF.union(n * (row - 1) + col, n * (row - 2) + col);
        }
        
        if (row == n) {
            sitesUF.union(n * (row - 1) + col, n * n + 1);
        } else if (isOpen(row + 1, col)) {
            sitesUF.union(n * (row - 1) + col, n * row + col);
        }

        if (col - 2 >= 0 && isOpen(row, col - 1)) {
            sitesUF.union(n * (row - 1) + col, n * (row - 1) + col - 1);
        }

        if (col <= n - 1 && isOpen(row, col + 1)) {
            sitesUF.union(n * (row - 1) + col, n * (row - 1) + col + 1);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return sites[row - 1][col - 1] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return sitesUF.connected(0, (row - 1) * n + col);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return sitesUF.connected(0, n * n + 1);
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}