import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTreeST<Value> {
    private Node root;
    private int size;
    private double maxX, mimX, maxY, mimY;

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
        if (this.size == 0) return true;
        return false;
    }

    // number of points
    public int size() {
        return this.size;
    }

    // associate the value val with point p
    public void put(Point2D p, Value val) {
        if (this.size == 0) {
            this.maxX = p.x();
            this.mimX = p.x();
            this.maxY = p.y();
            this.mimY = p.y();
        }

        if (this.maxX < p.x()) this.maxX = p.x();
        if (this.maxY < p.y()) this.maxY = p.y();
        if (this.mimX > p.x()) this.mimX = p.x();
        if (this.mimY > p.y()) this.mimY = p.y();

        this.root =  put(this.root, p, val, 0);
        return;
    }

    private Node put(Node root, Point2D p, Value val, int level) {
        if (root == null) {
            root = new Node(p, val);
            this.size += 1;
            return root;
        }

        if (level % 2 == 0) {
            if (p.x() < root.key.x())
                root.left = put(root.left, p, val, level + 1);
            else
                root.right = put(root.right, p, val, level + 1);
        }

        else {
            if (p.y() < root.key.y())
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

        if (root.key.equals(p))
            return root.value;

        if (level % 2 == 0) {
            if (p.x() < root.key.x())
                return get(root.left, p, level + 1);
            else
                return get(root.right, p, level + 1);
        }

        else {
            if (p.y() < root.key.y())
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

        if (root.key.equals(p))
            return true;

        if (level % 2 == 0) {
            if (p.x() < root.key.x())
                return contains(root.left, p, level + 1);
            else
                return contains(root.right, p, level + 1);
        }

        else {
            if (p.y() < root.key.y())
                return contains(root.left, p, level + 1);
            else
                return contains(root.right, p, level + 1);
        }

    }

    // all points in the symbol table
    public Iterable<Point2D> points() {
        if (this.isEmpty())
            return null;

        Queue<Point2D> all_keys = new Queue<Point2D>();
        Queue<Node> frontier = new Queue<Node>();
        Node temp = this.root;

        frontier.enqueue(temp);
        while (!frontier.isEmpty()) {
            temp = frontier.dequeue();
            if (temp == null)
                continue;
            all_keys.enqueue(temp.key);
            frontier.enqueue(temp.left);
            frontier.enqueue(temp.right);
        }

        return all_keys;

    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        Queue<Point2D> inRect = new Queue<Point2D>();
        range(rect, this.root, 0, this.maxX, this.mimX, this.maxY, this.mimY, inRect);
        return inRect;
    }

    private void range(RectHV rect, Node node, int level, double maX, double miX, double maY, double miY, Queue<Point2D> inRect) {
        if (node == null)
            return;

        if (rect.contains(node.key))
            inRect.enqueue(node.key);

        if (level % 2 == 0) {
            if (rect.intersects(new RectHV(node.key.x(), miX, maY, miY)))
                range(rect, node.left, level + 1, node.key.x(), miX, maY, miY, inRect);
            if (rect.intersects(new RectHV(maX, node.key.x(), maY, miY)))
                range(rect, node.right, level + 1, maX, node.key.x(), maY, miY, inRect);
        }

        else {
            if (rect.intersects(new RectHV(maX, miX, node.key.y(), miY)))
                range(rect, node.left, level + 1, maX, miX, node.key.y(), miY, inRect);
            if (rect.intersects(new RectHV(maX, miX, maY, node.key.y())))
                range(rect, node.right, level + 1, maX, miX, maY, node.key.y(), inRect);
        }
        return;
    }

    // a nearest neighbor of point p; null if the symbol table is empty
    public Point2D nearest(Point2D p) {
        return nearest(p, p, this.root, 0, this.maxX, this.mimX, this.maxY, this.mimY);
    }

    private Point2D nearest(Point2D p, Point2D closest, Node node, int level, double maX, double miX, double maY, double miY) {
        RectHV rect;

        if (p.equals(closest) || closest.distanceSquaredTo(p) > node.key.distanceSquaredTo(p))
            closest = node.key;

        if (level % 2 == 0) {

            rect = new RectHV(node.key.x(), miX, maY, miY);
            if (rect.distanceSquaredTo(p) < closest.distanceSquaredTo(p))
                closest = nearest(p, closest, node.left, level + 1, node.key.x(), miX, maY, miY);

            rect = new RectHV(maX, node.key.x(), maY, miY);
            if (rect.distanceSquaredTo(p) < closest.distanceSquaredTo(p))
                closest = nearest(p, closest, node.right, level + 1, maX, node.key.x(), maY, miY);
        }
        else {

            rect = new RectHV(maX, miX, node.key.y(), miY);
            if (rect.distanceSquaredTo(p) < closest.distanceSquaredTo(p))
                closest = nearest(p, closest, node.left, level + 1, maX, miX, node.key.y(), miY);

            rect = new RectHV(maX, miX, maY, node.key.y());
            if (rect.distanceSquaredTo(p) < closest.distanceSquaredTo(p))
                closest = nearest(p, closest, node.right, level + 1, maX, miX, maY, node.key.y());
        }

        return closest;

    }

    public Iterable<Point2D> nearest(Point2D p, int k) {
        return null;
    }

    // unit testing (required)
    public static void main(String[] args) {
        return;
    }
}
