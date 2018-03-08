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
        Node newNode = new Node();
        newNode.data = item;
        newNode.next = this.first;
        newNode.prev = null;
        //if it's the first incercion then first must also be the last
        if (this.last == null)
            this.last = newNode;
        this.first = newNode;
        return;
    }
    public void addLast(Item item) {
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
        return;
    }
    public Item removeFirst() {
        Item removed = this.first.data;
        this.first = this.first.next;
        return removed;
    }
    public Item removeLast() {
        Item removed = this.last.data;
        this.last = this.last.prev;
        return removed;

    }
    public Iterator<Item> iterator() {

    }
    public static void main(String[] args) {

    }
}