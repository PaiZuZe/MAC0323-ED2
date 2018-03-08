import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private Integer lenth;
    private class Node {
        Item data;
        Node next;
        Node prev;
    }
    public Deque() {
        this.first = null;
        this.last = null;
        this.lenth = 0;
    }

    public boolean isEmpty() {
        return this.lenth == 0;
    }

    public int size() {
        return this.lenth;
    }

    public void addFirst(Item item) {
        if (item == null)
            throw new java.lang.IllegalArgumentException("Null is not a valid entry for the Deque!\n");
        Node newNode = new Node();
        newNode.data = item;
        newNode.next = this.first;
        newNode.prev = null;
        //if it's the first incercion then first must also be the last
        if (this.last == null)
            this.last = newNode;
        this.first = newNode;
        this.lenth += 1;
        return;
    }

    public void addLast(Item item) {
        if (item == null)
            throw new java.lang.IllegalArgumentException("Null is not a valid entry for the Deque!\n");
        Node newNode = new Node();
        newNode.data = item;
        newNode.prev = this.last;
        newNode.next = null;
        if (this.last != null)
            this.last.next = newNode;
        //if it's the first incercion then first must also be the last
        else
            this.first = newNode;
        this.last = newNode;
        this.lenth += 1;
        return;
    }

    public Item removeFirst() {
        if (this.first == null)
            throw new java.util.NoSuchElementException("The Deque is empty!\n");
        Item removed = this.first.data;
        this.first = this.first.next;
        this.lenth -= 1;
        return removed;
    }

    public Item removeLast() {
        if (this.last == null)
            throw new java.util.NoSuchElementException("The Deque is empty!\n");
        Item removed = this.last.data;
        this.last = this.last.prev;
        this.lenth -= 1;
        return removed;

    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current;

        public DequeIterator() {
            this.current = first;
        }

        public boolean hasNext() {
            return current != null;
        }
        public Item next() {
            if (this.current == null)
                throw new java.util.NoSuchElementException("Deque has no elements left.\n");
            Node temp = this.current;
            this.current = this.current.next;
            return temp.data;
        }
        public void remove() {
            throw new java.lang.UnsupportedOperationException("Invalid operation.\n");
        }
    }

    public static void main(String[] args) {
        Deque<String> testDeque = new Deque<String>();

        StdOut.print("Is the Deque empty ? " + testDeque.isEmpty() + "\n");
        StdOut.print("What is the size of the Deque ? " + testDeque.size() + "\n");

        //Testing the addition to the end and the begginig of the Deque.
        StdOut.print("Please type 10 inputs for the Deque.\n");
        for (int i = 0; i < 10; i++) {
            String word = StdIn.readString();
            if (i%2 == 0)
                testDeque.addFirst(word);
            else
                testDeque.addLast(word);
        }
        StdOut.print("is the Deque empty ? " + testDeque.isEmpty() + "\n");
        StdOut.print("What is the size of the Deque ? " + testDeque.size() + "\n");

        //Testing the iterator.
        StdOut.print("Now we will test the iterator.\n");
        Iterator<String> testIterator = testDeque.iterator();
        for (int i = 0; i < 10; i++) {
            if (testIterator.hasNext())
                StdOut.print(testIterator.next() + " ");
        }
        StdOut.print("\n");

        //Testing the removal from the end and the begginig of the Deque.
        StdOut.print("Now we will test the removal from the Deque.\n");
        for (int i = 0; i < 10; i++) {
            if (i%2 == 0)
                StdOut.print(testDeque.removeFirst() + " ");
            else
                StdOut.print(testDeque.removeLast() + " ");
        }
        StdOut.print("\n");

        StdOut.print("Testar a exception do remove().\n");
        testIterator.remove();

        return;
    }
}