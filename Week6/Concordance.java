public class Concordance {
    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] words = in.readAllStrings();
        ST<String, SET<Integer>> st = new ST<String, SET<Integer>>();
        for (int i = 0; i < words.length; i++) {
            String s = words[i];
            if (!st.contains(s))
                st.put(s, new SET<Integer>());
            SET<Integer> set = st.get(s);
            set.add(i);
        }

        while (!StdIn.isEmpty()) {
            String query = StdIn.readString();
            SET<Integer> set = st.get(query);
            for (int k : set)
                ;// print words[k-4] to words[k+4]
        }
    }
}