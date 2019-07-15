import java.util.NoSuchElementException;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] itemArray;
    private int N = 0; // current position 

    public RandomizedQueue() {
        itemArray = (Item []) new Object[1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (N == itemArray.length) resize(2 * itemArray.length);
        itemArray[N++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int ranIndex = StdRandom.uniform(N); // returns a random integer uniformly in [0, N)
        Item item = itemArray[ranIndex];
        itemArray[ranIndex] = itemArray[--N];
        itemArray[N] = null;
        if (N > 0 && N == itemArray.length / 4) {
            resize(itemArray.length / 2);
        }
        return item;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        int ranIndex = StdRandom.uniform(N);
        Item item = itemArray[ranIndex];
        return item;
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private void resize(int capacity) {
        Item[] copy = (Item []) new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = itemArray[i];
        }
        itemArray = copy;
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i = N;
        private int[] perm = StdRandom.permutation(N);

        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return itemArray[perm[--i]];
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> q1 = new RandomizedQueue<>();
        // ... 
    }
}