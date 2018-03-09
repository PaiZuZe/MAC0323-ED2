import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int last;
    private int lenth;
    private Item[] queue;
    //private int ocupied;

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.last = -1;
        this.lenth = 1;
        //this.ocupied = 0;
        this.queue = (Item[]) new Object[1];
    }
    // is the randomized queue empty?
    public boolean isEmpty() {
        return this.last == -1;
    }
    // return the number of items on the randomized queue
    public int size() {
        return this.last + 1;
    }
    // add the item
    public void enqueue(Item item) {
        if (this.last == this.lenth - 1)
            this.resizeUp();
        this.last += 1;
        this.queue[last] = item;
        //this.ocupied += 1;
    }
    // remove and return a random item
    public Item  dequeue() {
        int toRemove = StdRandom.uniform(this.last + 1);
        Item temp = this.queue[toRemove];
        this.queue[toRemove] = this.queue[this.last];
        this.last -= 1;
        return temp;
    }
    // return a random item (but do not remove it)
    public Item sample() {
        return(this.queue[StdRandom.uniform(this.last + 1)]);
    }

    private void resizeUp() {
        Item[] newQueue = (Item[]) new Object[this.lenth*2];
        for (int i = 0; i < this.last + 1; i++)
            newQueue[i] = this.queue[i];
        this.queue = newQueue;
        this.lenth *= 2;
        return;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item>
    {
        private int current;
        private Item[] tempQueue;
        public QueueIterator() {
            this.current = -1;
            this.tempQueue = (Item[]) new Object[last + 1];
            for (int i = 0; i < last + 1; i++)
                tempQueue[i] = queue[i];
            StdRandom.shuffle(tempQueue);
        }

        public boolean hasNext() {
            return this.current <= last;
        }

        public Item next() {
            if (!this.hasNext())
                    throw new java.util.NoSuchElementException("RandomizedQueue has no elements left.\n");
            this.current += 1;
            return tempQueue[this.current];
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException("Invalid operation.\n");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> testQueue = new RandomizedQueue<String>();
        StdOut.print("Is the Randomized Queue empty ? " + testQueue.isEmpty() + "\n");
        StdOut.print("What is the size of the Randomized Queue ? " + testQueue.size() + "\n");

        //Testing the addition to the Randomized Queue.
        StdOut.print("Please type 10 inputs for the Randomized Queue.\n");
        for (int i = 0; i < 10; i++) {
            String word = StdIn.readString();
            testQueue.enqueue(word);
        }
        StdOut.print("is the Randomized Queue empty ? " + testQueue.isEmpty() + "\n");
        StdOut.print("What is the size of the Randomized Queue ? " + testQueue.size() + "\n");

        //Testing the iterator.
        StdOut.print("Now we will test the iterator.\n");
        Iterator<String> testIterator = testQueue.iterator();
        for (int i = 0; i < 10; i++) {
            if (testIterator.hasNext())
                StdOut.print(testIterator.next() + " ");
        }
        StdOut.print("\n");

        //Testing the removal from the end and the begginig of the Randomized Queue.
        StdOut.print("Now we will test the removal from the Randomized Queue.\n");
        for (int i = 0; i < 10; i++)
            StdOut.print(testQueue.dequeue() + " ");
        StdOut.print("\n");

        StdOut.print("Testar a exception do remove().\n");
        testIterator.remove();

    }
}
