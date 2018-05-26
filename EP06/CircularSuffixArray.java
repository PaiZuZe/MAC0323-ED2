import edu.princeton.cs.algs4.StdOut;
import java.util.Comparator;
import edu.princeton.cs.algs4.MinPQ;

public class CircularSuffixArray {
    private int len;
    private int[] indexes;
    private MinPQ<helper> pq;

    private class helper {
        int pos;

        private helper(int i) {
            this.pos = i;
            return;
        }
    }

    public class StringComparator implements Comparator<helper>{
        private String s;
        private int n;

        public StringComparator(String text, int lenth) {
            s = text;
            n = lenth;
            return;
        }

        @Override
        public int compare(helper a, helper b) {
            for (int i = 0; i < this.n; i++) {
                if (this.s.charAt((a.pos + i) % this.n) == this.s.charAt((b.pos + i) % this.n))
                    continue;
                else if (this.s.charAt((a.pos + i) % this.n) < this.s.charAt((b.pos + i) % this.n))
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
            throw new java.lang.IllegalArgumentException("Argument s can't be null.\n");
        this.len = s.length();
        this.indexes = new int[this.len];
        this.pq = new MinPQ<helper>(new StringComparator(s, this.len));
        for (int i = 0; i < this.len; i++)
            pq.insert(new helper(i));

        for (int i = 0; i < this.len; i++)
            this.indexes[i] = pq.delMin().pos;
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
        CircularSuffixArray bob = new CircularSuffixArray("ABRACADABRA!");
        for (int i = 0; i < bob.length(); i++) {
            StdOut.print(bob.index(i) + "\n");
        }
    }
}
