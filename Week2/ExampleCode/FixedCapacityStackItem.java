public class FixedCapacityStack<Item> {
    private Item[] s;
    private int N = 0;

    public FixedCapacityStack(int capacity) {
        // s = new Item[capacity];    // Java does not allow generics array creation.
        s = (Item[]) new Object[capacity]; // ugly casting.
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(Item item) {
        s[N++] = item;
    }

    // avoid loitering.
    public Item pop() {
        Item item = s[--N]; // decrement N; then use to index into array
        s[N] = null;
        return item;
    }
}