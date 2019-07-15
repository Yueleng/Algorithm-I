import java.io.File;
public class FileIndex {
    public static void main(String[] args) {
        // symbol table
        ST<String, SET<File>> st = new ST<String, SET<File>>();

        // list of filenames from command line
        for (String filename : args) {
            File file = new File(filename);
            In in = new In(file);
            while (!in.isEmpty()) {
                // for each word in file, add file to corresponding set.
                String key = in.readString();
                if (!st.contains(key))
                    st.put(key, new SET<File>());
                SET<File> set = st.get(key);
                // modification in set will affect the set within st.
                set.add(file);
            }
        }

        while (!StdIn.isEmpty()) {
            String query = StdIn.readString();
            StdOut.println(st.get(query));
        }
    }
}