public class Merge {
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid);    // precondition: a[lo..mid]    sorted 
        assert isSorted(a, mid+1, hi);  // precondition: a[mid+1..hi]  sorted

        // copy
        for (int k = lo; k <= hi; k++)
            auk[k] = a[k];

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) 
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (less(aux[j], aux[i]))
                a[k] = aux[j++];
            else 
                a[k] = aux[i++]; // if aux[j] == aux[i], aux[i] has priority.
        }

        assert isSorted(a, lo, hi);
    }

    // private method, only called by overloaded public sort method.
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid+1, hi);
        merge(a, aux, lo, mid, hi);
    }

    // public overloaded sort method
    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        int N = a.length;
        for (int i = 0; i < N-1; i++)
            if (less(a[i+1], a[i])) 
                return false;
        return true;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
}