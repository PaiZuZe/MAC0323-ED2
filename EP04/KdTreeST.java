import edu.princeton.cs.algs4.Queue;

public class KdTreeST<Value> {
    private Node root;
    private int size;

    private class Node {
        private Point2D key;
        private Value value;
        private Node left;
        private Node right;

        private Node(Point2D key, Value value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            return;
        }
    }

    // construct an empty symbol table of points
    public KdTreeST() {
        this.root = null;
        this.size = 0;
        return;
    }

    // is the symbol table empty?
    public boolean isEmpty() {
        return size;
    }

    // number of points
    public int size() {
        return size;
    }

    // associate the value val with point p
    public void put(Point2D p, Value val) {
        this.root =  put(this.root, p, val, 0);
        return;
    }

    private Node put(Node root, Point2D p, Value val, int level) {
        if (root == null) {
            root = new Node(p, val);
            this.size += 1;
            return root;
        }

        if (level % 2 == 1) {
            if (p.x < root.key.x)
                root.left = put(root.left, p, val, level + 1);
            else
                root.right = put(root.right, p, val, level + 1);
        }

        else {
            if (p.y < root.key.y)
                root.left = put(root.left, p, val, level + 1);
            else
                root.right = put(root.right, p, val, level + 1);
        }

        return root;
    }

    // value associated with point p
    public Value get(Point2D p) {
        return get(root, p, 0);
    }

    private Value get(Node root, Point2D p, int level) {
        if (root == null)
            return null;

        if (root.key.compareTo(p))
            return root.value;

        if (level % 2 == 1) {
            if (p.x < root.key.x)
                return get(root.left, p, level + 1);
            else
                return get(root.right, p, level + 1);
        }

        else {
            if (p.y < root.key.y)
                return get(root.left, p, level + 1);
            else
                return get(root.right, p, level + 1);
        }

    }

    // does the symbol table contain point p?
    public boolean contains(Point2D p) {
        return contains(root, p, 0);
    }

    private boolean contains(Node root, Point2D p, int level) {
        if (root == null)
            return false;

        if (root.key.compareTo(p))
            return true;

        if (level % 2 == 1) {
            if (p.x < root.key.x)
                return get(root.left, p, level + 1);
            else
                return get(root.right, p, level + 1);
        }

        else {
            if (p.y < root.key.y)
                return get(root.left, p, level + 1);
            else
                return get(root.right, p, level + 1);
        }

    }

    // all points in the symbol table
    public Iterable<Point2D> points() {
        Queue<Point2D> all_keys = new Queue<Point2D>();
        

    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {

    }

    // a nearest neighbor of point p; null if the symbol table is empty
    public Point2D nearest(Point2D p) {

    }

    public Iterable<Point2D> nearest(Point2D p, int k) {

    }

    // unit testing (required)
    public static void main(String[] args) {

    }
}
