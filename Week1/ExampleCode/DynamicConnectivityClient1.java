class DynamicConnectivityClient1 {
    public static void main(String[] args) {
        int N = StdIn.readInt();
        UF uf = new UF(N);
        while (!StdIn.isEmpty()) {
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
    }
}
