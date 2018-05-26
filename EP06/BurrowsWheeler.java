import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.StdOut;

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
            //StdOut.print(inputTxt.charAt(temp) + "\n");
            BinaryStdOut.write(inputTxt.charAt(temp));
        }
        BinaryStdOut.flush();
    }

    // apply Burrows-Wheeler inverse transform, reading from standard input and writing to standard output
    public static void inverseTransform() {

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
