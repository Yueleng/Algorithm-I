// about 10% slower than recursive top-down mergesort on typical systems.

public class MergeBU {
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

    // no recursive calls 
    public static void sort(Comparable[] a) {
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz + sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                merge(a,aux, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
            }
        }
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