/**
 * A significant improvement of QuickUnionUF 
 */

public class QuickUnionUF {
    private int[] id;
    private int[] sz;

    public QuickUnionUF(int N){
        id = new int[N];
        sz = new int[N];
        for (int i=0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    private int root(int i) {
        // chase parent pointers unitil reach root 
        // depth of i array accesses.
        while (i != id[i]) {
            id[i] = id[id[i]]; // tree compression
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        // change root of p to point to root of q.
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        if (sz[i] < sz[j]) {
            id[i] = j; sz[j] += sz[i];
        } else {
            id[j] = i; sz[i] += sz[j];
        }
    }
}