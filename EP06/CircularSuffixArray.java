import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.Comparator;
import java.lang.Integer;
import java.util.Arrays;


public class CircularSuffixArray {
    private int len;
    private Integer[] indexes;

    public class Quick3stringModified {
        private String s;
        private int n;
        private static final int CUTOFF =  15;

        private Quick3stringModified(String text, int lenth) {
            s = text;
            n = lenth;
            return;
        }

        public void sort(Integer[] a) {
            sort(a, 0, n - 1, 0);
            return;
        }

        private int charAt(Integer index, int d) {
            assert d >= 0 && d <= this.n;
            if (d == this.n) return -1;
            return s.charAt((index + d) % this.n);
        }


        private void sort(Integer[] a, int lo, int hi, int d) {
            if (hi <= lo + CUTOFF) {
                insertion(a, lo, hi, d);
                return;
            }

            int lt = lo, gt = hi;
            int v = charAt(a[lo], d);
            int i = lo + 1;
            while (i <= gt) {
                int t = charAt(a[i], d);
                if      (t < v) exch(a, lt++, i++);
                else if (t > v) exch(a, i, gt--);
                else              i++;
            }
            sort(a, lo, lt-1, d);
            if (v >= 0) sort(a, lt, gt, d+1);
            sort(a, gt+1, hi, d);
        }

        private void insertion(Integer[] a, int lo, int hi, int d) {
            for (int i = lo; i <= hi; i++)
                for (int j = i; j > lo && less(a[j], a[j-1], d); j--)
                    exch(a, j, j-1);
        }

        private void exch(Integer[] a, int i, int j) {
            Integer temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            return;
        }

        private boolean less(Integer v, Integer w, int d) {

            for (int i = 0; i < this.n; i++) {
                if (this.s.charAt((v + i + d) % this.n) == this.s.charAt((w + i + d) % this.n))
                    continue;
                else if (this.s.charAt((v + i + d) % this.n) < this.s.charAt((w + i + d) % this.n))
                    return true;
                else
                    return false;
            }
            return false;
        }

    }

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null)
            throw new java.lang.IllegalArgumentException("Arguments can't be null.\n");

        this.len = s.length();
        this.indexes = new Integer[this.len];
        for (int i = 0; i < this.len; i++) indexes[i] = i;
        Quick3stringModified blop = new Quick3stringModified(s, this.len);
        blop.sort(indexes);

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
