import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.StdOut;


public class MoveToFront {
    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        char[] alphabet = new char[256];
        int i = 0;
        char c = BinaryStdIn.readChar();
        for (i = 0; i < 256; i++) alphabet[i] = (char) i;

        while (!BinaryStdIn.isEmpty()) {
            for (i = 0; i < 256; i++) if (c == alphabet[i]) break;
            for (int j = i - 1; j >= 0; j--) alphabet[j + 1] = alphabet[j];
            alphabet[0] = c;
            c = BinaryStdIn.readChar();
            BinaryStdOut.write( (char) i);
        }
        BinaryStdOut.flush();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
        char[] alphabet = new char[256];
        int i = 0;
        char temp;
        char c = BinaryStdIn.readChar();
        for (i = 0; i < 256; i++) alphabet[i] = (char) i;

        while (!BinaryStdIn.isEmpty()) {
            i = (int) c;
            BinaryStdOut.write(alphabet[i]);
            temp = alphabet[i];
            for (int j = i - 1; j >= 0; j--) alphabet[j + 1] = alphabet[j];
            alphabet[0] = temp;
            c = BinaryStdIn.readChar();
        }
        i = (int) c;
        BinaryStdOut.write(alphabet[i]);

        BinaryStdOut.flush();
    }

    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
    public static void main(String[] args) {
        if (args[0].compareTo("-") == 0)
            encode();
        else if (args[0].compareTo("+") == 0)
            decode();
    }
}
