public class Quick {
    public static final int CUTOFF = 10;
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            while (less(a[++i], a[lo]))
                if (i == hi) break;
            while (less(a[lo], a[--j]))
                if (j == lo) break;

            if (i >= j) break;
            exch(a, i, j);
        }

        exch(a, lo, j);
        return j;
    }

    public static void sort(Comparable [] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        // if (hi <= lo) return;
        if (hi <= lo + CUTOFF - 1) {
            Insertion.sort(a, lo, hi);
            return;
        };

        int m = medianOf3(a, lo, lo + (hi - lo) / 2, hi);
        swap(a, lo, m);

        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j, hi);
    }

    public static void sortWithDup(Comparablep[] a) {
        StdRandom.shuffle(a);
        sortWithDup(a, 0, a.length - 1);
    }

    private static void sortWithDup(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        Comparable v = a[lo];
        int i = lo;
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else i++;
        }

        sort(a, lo, lt-1);
        sort(a, gt + 1, hi);
    }



    public static Comparable select(Comparable[] a, int k) {
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int j = partition(a, lo, hi);
            if (j < k) lo = j + 1;
            else if (j > k) hi = j - 1;
            else return a[k];
        }

        return a[k];
    }
}