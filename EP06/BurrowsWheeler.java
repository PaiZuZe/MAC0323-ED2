import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.StdOut;
import java.lang.Integer;
import java.util.LinkedList;

public class BurrowsWheeler {
    // apply Burrows-Wheeler transform, reading from standard input and writing to standard output
    public static void transform() {
        int temp;
        String inputTxt = "";
        CircularSuffixArray cirArray;

        while (!BinaryStdIn.isEmpty()) inputTxt += BinaryStdIn.readChar();
        cirArray = new CircularSuffixArray(inputTxt);

        for (int i = 0; i < cirArray.length(); i++) if (cirArray.index(i) == 0) BinaryStdOut.write(i);

        for (int i = 0; i < cirArray.length(); i++) {
            temp = cirArray.index(i);
            if ((temp - 1) % cirArray.length() < 0)
                temp = cirArray.length() - 1;
            else
                temp = (temp - 1) % cirArray.length();
            BinaryStdOut.write(inputTxt.charAt(temp));
        }
        BinaryStdOut.flush();
    }

    // apply Burrows-Wheeler inverse transform, reading from standard input and writing to standard output
    public static void inverseTransform() {
        class Helper {
            int freq;
            LinkedList<Integer> poss;
            Helper() {
                this.freq = 0;
                this.poss = new LinkedList<Integer>();
                return;
            }
            void add(Integer pos) {
                this.freq += 1;
                this.poss.add(pos);
                return;
            }
        }

        int first, i;
        char c;
        Helper[] freq = new Helper[256];
        String inputTxt = "";

        for (i = 0; i < 256; i++) freq[i] = new Helper();
        first = BinaryStdIn.readInt();
        i = 0;
        while (!BinaryStdIn.isEmpty()){
            c = BinaryStdIn.readChar();
            freq[(int) c].add(new Integer(i));
            inputTxt += c;
            i++;
        }
        //Se sorted[first] me da o prim, inputTxt[first] me da o último.

    }

    // if args[0] is '-', apply Burrows-Wheeler transform
    // if args[0] is '+', apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (args[0].compareTo("-") == 0)
            transform();
        else if (args[0].compareTo("+") == 0)
            inverseTransform();
    }
}
