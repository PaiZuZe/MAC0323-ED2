import java.util.Comparator;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.Queue;

public class PointST<Value> {
    private RedBlackBST<Point2D, Value> ST;
    private MaxPQ<Point2D> kNearest;
    private Queue<Point2D> inRect;

    public class Point2Dcomparator implements Comparator<Point2D> {
        private Point2D origin;
        public Point2Dcomparator(Point2D org) {
            origin = org;
            return;
        }

        @Override
        public int compare(Point2D a, Point2D b) {
            if (a.distanceSquaredTo(origin) == b.distanceSquaredTo(origin))
                return 0;
            else if (a.distanceSquaredTo(origin) < b.distanceSquaredTo(origin))
                return -1;
            else
                return 1;
        }
    }


    // construct an empty symbol table of points
    public PointST() {
        this.ST = new RedBlackBST<Point2D, Value>();
        return;
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
        if (p == null || val == null)
            throw new java.lang.IllegalArgumentException("p or val can't be null.\n");

        this.ST.put(p, val);
        return;
    }

    // value associated with point p
    public Value get(Point2D p) {
        if (p == null)
            throw new java.lang.IllegalArgumentException("p can't be null.\n");

        return this.ST.get(p);
    }

    // does the symbol table contain point p?
    public boolean contains(Point2D p) {
        if (p == null)
            throw new java.lang.IllegalArgumentException("p can't be null.\n");

        return this.ST.contains(p);
    }

    // all points in the symbol table
    public Iterable<Point2D> points() {
        return this.ST.keys();
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new java.lang.IllegalArgumentException("rect can't be null.\n");

        this.inRect = new Queue<Point2D>();
        for (Point2D temp : this.ST.keys())
            if (rect.contains(temp))
                this.inRect.enqueue(temp);
        return this.inRect;
    }

    // a nearest neighbor of point p; null if the symbol table is empty
    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new java.lang.IllegalArgumentException("p can't be null.\n");

        if (this.isEmpty())
            return null;

        Point2D closest = null;
        for (Point2D temp : this.ST.keys())
            if (closest == null || p.distanceSquaredTo(temp) < p.distanceSquaredTo(closest))
                closest = temp;
        return closest;
    }

    public Iterable<Point2D> nearest(Point2D p, int k) {
        if (p == null)
            throw new java.lang.IllegalArgumentException("p can't be null.\n");

        this.kNearest = new MaxPQ<Point2D>(new Point2Dcomparator(p));

        if (this.isEmpty())
            return kNearest;

        for (Point2D temp : this.ST.keys()) {

            if (this.kNearest.size() < k) {
                kNearest.insert(temp);
                continue;
            }

            if (kNearest.max().distanceSquaredTo(p) > temp.distanceSquaredTo(p)) {
                kNearest.insert(temp);
                kNearest.delMax();
            }
        }
        return kNearest;
    }

    // unit testing (required)
    public static void main(String[] args) {
        return;
    }
}
