import java.util.NoSuchElementException;
import java.util.Iterator;
public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int len;

    
    private class Node {
        Item item;   
        Node next;
        Node prev;
    }

    public Deque() {
        first = null;
        last = null;
        len = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return len;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (len == 0) {
            first = new Node();
            first.item = item;
            first.next = null;
            first.prev = null;
            last = first;
        } else {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            first.prev = null;
            oldfirst.prev = first;
        }
        len += 1;
    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (len == 0) {
            last = new Node();
            last.item = item;
            last.next = null;
            last.prev = null;
            first = last;
        } else {
            Node oldlast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            last.prev = oldlast;
            oldlast.next = last;
        }
        len += 1;
    }

    public Item removeFirst() {
        if (len == 0) throw new NoSuchElementException();
        if (len == 1) {
            len = 0;
            Node oldfirst = first;
            first = null;
            last = null;
            return oldfirst.item;
        } else {
            len -= 1;
            Node oldfirst = first;
            first = first.next;
            first.prev = null;
            return oldfirst.item;
        }
    }

    public Item removeLast() {
        if (len == 0) throw new NoSuchElementException();
        if (len == 1) {
            len = 0;
            Node oldlast = last;
            last = null;
            first = null;
            return oldlast.item;
        } else {
            len -= 1;
            Node oldlast = last;
            last = last.prev;
            last.next = null;
            return oldlast.item;
        }
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            current.prev = null;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
    }
}