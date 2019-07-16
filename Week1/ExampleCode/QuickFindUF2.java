public class QuickFindUF2 {
    public int[] id;

    // Constructor
    public QuickFindUF2(int N) {
        id = new int[N];

        // set id of each object to itself (N array accesses)
        for (int i=0; i < N; i++)
            id[i] = i;
    }

    // check whether p and q are in the same component
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    // change all entries with id[p] to id[q](qid)
    // at most 2N+2? array access
    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i=0; i < id.length; i++) {
            if (id[i] == pid) id[i] = qid;
        }
    }
}