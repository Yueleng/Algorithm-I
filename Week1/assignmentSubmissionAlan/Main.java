import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

class Main {
    public static void main(String[] args) {
        int[] sitesIndex = new int[10];
        for (int j = 0; j < 10; j++) {
            sitesIndex[j] = j;
        }

        for (int j = 0; j < 10; j++) {
            System.out.println(sitesIndex[j]);
        }
        // sitesIndex = StdRandom.shuffle(sitesIndex);
        // for (int j = 0; j < 10; j++) {
        //     System.out.println(sitesIndex);
        // }
    }
}