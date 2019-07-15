public class SparseVector {
    private HashST<Integer, Double> v;

    public SparseVector() {
        v = new HashST<Integer, Double> ();
    }

    public void put(int i, double x) {
        v.put(i, x);
    }

    public double get(int i) {
        if (!v.contains(i)) return 0.0;
        else return v.get(i);
    }

    public Iterable<Integer> indices() {
        return v.keys();
    }
    
    public double dot(double[] that) {
        double sum = 0.0;
        for (int i : indices())
            sum += that[i] * this.get(i);
        return sum;    
    }
}