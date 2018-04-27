import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.Queue;

public class PointST<Value> {
    private RedBlackBST<Point2D, Value> ST;
    private Queue<Point2D> inRect;

    // construct an empty symbol table of points
    public PointST() {
        return this.ST = new RedBlackBST<Point2D, Value>();
    }

    // is the symbol table empty?
    public boolean isEmpty() {
        return this.ST.isEmpty();
    }

    // number of points
    public int size() {
        return this.ST.size();
    }

    // associate the value val with point p
    public void put(Point2D p, Value val) {
        this.ST.put(p, val);
        return;
    }

    // value associated with point p
    public Value get(Point2D p) {
        return this.ST.get(p);
    }

    // does the symbol table contain point p?
    public boolean contains(Point2D p) {
        return this.ST.contains(p);
    }

    // all points in the symbol table
    public Iterable<Point2D> points() {
        return this.ST.keys();
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        this.inRect = new Queue<Point2D>();
        for (Point2D temp : this.ST)
            if (rect.contains(temp))
                this.inRect.enqueue(temp);
        return this.inRect;
    }

    // a nearest neighbor of point p; null if the symbol table is empty
    public Point2D nearest(Point2D p) {
        if (this.isEmpty())
            return null;
        Point2D closer = p;
        for (Point2D temp : this.ST)
            if (p.equals(closser) || p.distanceSquaredTo(temp) < p.distanceSquaredTo(closer))
                closer = temp;
        return closer;
    }

    public Iterable<Point2D> nearest(Point2D p, int k) {
        return null;
    }

    // unit testing (required)
    public static void main(String[] args) {
        return;
    }
}
