import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty())
            queue.enqueue(StdIn.readString());
        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            StdOut.print(queue.dequeue() + "\n");
        }
    }
}