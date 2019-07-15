public class MergeSort {
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid);    // precondition: a[lo..mid]    sorted 
        assert isSorted(a, mid+1, hi);  // precondition: a[mid+1..hi]  sorted

        // no copy here
        
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
        final int CUTOFF = 7;
        if (hi <= lo  + CUTOFF - 1) {
            Insertion.sort(a, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(aux, a, lo, mid);
        sort(aux, a, mid+1, hi);
        if (!less(a[mid+1], a[mid])) return; // helps for partially-ordered arrays.
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