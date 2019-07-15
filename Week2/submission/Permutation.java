import java.util.Iterator;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> ranQueue = new RandomizedQueue<>();
        // the following code fragment reads integers from standard input,
        // one at a time, and prints them one per line.
        // while (!StdIn.isEmpty()) {
        //     double value = StdIn.readDouble();
        //     StdOut.println(value);
        // }
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            ranQueue.enqueue(item);
        }

        Iterator<String> iter = ranQueue.iterator();

        for (int i = 0; i < k; i++) {
            StdOut.println(iter.next());
        }

    }
}