import java.util.Iterator;
import stdRamdom.uniform;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int last;
    private int lenth;
    //private int ocupied;

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.last = -1;
        this.lenth = 1;
        //this.ocupied = 0;
        Item[] queue = (Item[]) new object[1];
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
        if (this.last == this.lenth)
            queue.resize();
        this.last += 1;
        queue[last] = item;
        //this.ocupied += 1;
    }
    // remove and return a random item
    public Item dequeue() {
        int toRemove = srtRamdom.uniform(this.last + 1);
        Item temp = queue[toRemove];
        queue[toRemove] = queue[this.last];
        this.last -= 1;
        return temp;
    }
    // return a random item (but do not remove it)
    public Item sample() {
        return(queue[srtRamdom.uniform(this.last + 1)]);
    }

    private resize() {
        Item[] newQueue = (Item[]) new object[this.lenth*2];
        for (int i = 0; i < this.last + 1; i++)
            newQueue[i] = queue[i];
        queue = newQueue;
        this.lenth *= 2;
        return;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new queueIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {

    }
}