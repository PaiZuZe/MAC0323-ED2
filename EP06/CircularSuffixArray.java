import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.Comparator;
import java.lang.Integer;
import java.util.Arrays;


public class CircularSuffixArray {
    private int len;
    private Integer[] indexes;

    public class StringComparator implements Comparator<Integer>{
        private String s;
        private int n;

        public StringComparator(String text, int lenth) {
            s = text;
            n = lenth;
            return;
        }

        @Override
        public int compare(Integer a, Integer b) {
            for (int i = 0; i < this.n; i++) {
                if (this.s.charAt((a + i) % this.n) == this.s.charAt((b + i) % this.n))
                    continue;
                else if (this.s.charAt((a + i) % this.n) < this.s.charAt((b + i) % this.n))
                    return -1;
                else
                    return 1;
            }
            return 0;
        }
    }

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null)
            throw new java.lang.IllegalArgumentException("Arguments can't be null.\n");

        this.len = s.length();
        this.indexes = new Integer[this.len];
        for (int i = 0; i < this.len; i++) indexes[i] = i;
        Arrays.sort(indexes, new StringComparator(s, this.len));

    }

    // length of s
    public int length() {
        return this.len;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i >= this.len)
            throw new java.lang.IllegalArgumentException("Argument i out of bounds.\n");

        return indexes[i];
    }

    // unit testing (required)
    public static void main(String[] args) {
        String s = StdIn.readAll();
        CircularSuffixArray bob = new CircularSuffixArray(s);
        if (args.length > 0)
            if (args[0].compareTo("v") == 0)
                for (int i = 0; i < bob.length(); i++) {
                    for (int j = 0; j < bob.length(); j++) StdOut.print(s.charAt((bob.index(i) + j) % bob.length()));
                    StdOut.print("\n");
                }
    }
}
