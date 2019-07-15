public class FixedCapacityStackOfStrings {
    private String[] s;
    private int N = 0; // current position

    // it breaks the API, by require the client to provide the capacity.
    public FixedCapacityStackOfStrings(int capacity) {
        s = new String[capacity];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(String item) {
        s[N++] = item; // use to index into array; then increment N
    }

    // avoid loitering.
    public String pop() {
        String item = s[--N]; // decrement N; then use to index into array
        s[N] = null;
        return item;
    }
}