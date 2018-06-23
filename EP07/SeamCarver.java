import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.Stack;
import java.util.Objects;
import java.lang.Math;
import java.util.Iterator;
import java.util.HashMap;
import java.util.LinkedList;
import java.awt.Color;

public class SeamCarver {
    private Picture image;
    private double energy[][];
    private int rows;
    private int columns;

    private class Path {
        private double cost;
        private int path[];
        private int count;

        public Path(int size) {
            this.path = new int[size];
            this.cost = 0;
            this.count = size - 1;
        }

        public void addCost(double x) {
            this.cost += x;
        }

        public void addToPath(int index) {
            path[count--] = index;
        }

        public int[] curPath() {
            return this.path;
        }

    }

    private class Pixel implements Comparable<Pixel> {
        int x;
        int y;
        Boolean flag;
        double cost;

        public Pixel(int x, int y, Boolean flag) {
            this.x = x;
            this.y = y;
            this.flag = flag;
            if (x != -1 && y != -1)
                this.cost = energy[x][y];
            else
                this.cost = 0;
            return;
        }

        public Iterator<Pixel> neighboors() {
            LinkedList<Pixel> neig = new LinkedList<Pixel>();
            if (flag) {
                if (x == -1 && y == -1) {
                    for (int i = 0; i < columns; i++) {
                        neig.add(new Pixel(i, 0, this.flag));
                    }
                    return neig.listIterator();
                }
                if (y == rows - 1)
                    return null;
                if (this.x > 0)
                    neig.add(new Pixel(this.x - 1, this.y + 1, this.flag));
                neig.add(new Pixel(this.x, this.y + 1, this.flag));
                if (this.x < columns - 1)
                    neig.add(new Pixel(this.x + 1, this.y + 1, this.flag));
            }
            else {
                if (x == -1 && y == -1) {
                    for (int j = 0; j < rows; j++) {
                        neig.add(new Pixel(0, j, this.flag));
                    }
                    return neig.listIterator();
                }
                if (x == columns - 1)
                    return null;
                if (this.y > 0)
                    neig.add(new Pixel(this.x + 1, this.y - 1, this.flag));
                neig.add(new Pixel(this.x + 1, this.y, this.flag));
                if (this.y < rows - 1)
                    neig.add(new Pixel(this.x + 1, this.y + 1, this.flag));
            }
            return neig.listIterator();
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }


        @Override
        public boolean equals(Object other) {
            if (other == this)
                return true;
            if (!(other instanceof Pixel))
                return false;
            if (this.x == ((Pixel) other).x && this.y == ((Pixel) other).y)
                return true;
            return false;
        }

        public int compareTo(Pixel other) {
            if (this.cost == other.cost)
                return 0;
            else if (this.cost < other.cost)
                return -1;
            else
                return 1;

        }
    }

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null)
            throw new java.lang.IllegalArgumentException("Picture can't be null\n");
        this.image = new Picture(picture);
        this.rows = this.image.height();
        this.columns = this.image.width();
    }

    // current picture
    public Picture picture() {
        return this.image;
    }

    // width of current picture
    public int width() {
        return this.columns;
    }

    // height of current picture
    public int height() {
        return this.rows;
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x < 0 || x > this.columns || y < 0 || y > this.rows)
            throw new java.lang.IllegalArgumentException("X or y out of range\n");
        double delX = 0.0;
        double delY = 0.0;
        int cor1 = this.image.getRGB(module(x - 1, this.columns), y);
        int cor2 = this.image.getRGB(module(x + 1, this.columns), y);
        int cor3 = this.image.getRGB(x, module(y - 1, this.rows));
        int cor4 = this.image.getRGB(x, module(y + 1, this.rows));
        delX += square(((cor1 >> 16) & 0xFF) - ((cor2 >> 16) & 0xFF))
                + square(((cor1 >> 8) & 0xFF) - ((cor2 >> 8) & 0xFF))
                + square(((cor1 >> 0) & 0xFF) - ((cor2 >> 0) & 0xFF));
        delY += square(((cor3 >> 16) & 0xFF) - ((cor4 >> 16) & 0xFF))
                + square(((cor3 >> 8) & 0xFF) - ((cor4 >> 8) & 0xFF))
                + square(((cor3 >> 0) & 0xFF) - ((cor4 >> 0) & 0xFF));
        return(Math.sqrt(delX + delY));
    }

    public int[] findHorizontalSeam() {
        Path blah[] = new Path[this.rows];
        int min = 0;
        this.energy = new double[this.columns][this.rows];
        for (int i = 0; i < this.columns; i++)
            for (int j = 0; j < this.rows; j++)
                energy[i][j] = energy(i, j);

        return findPath(false).curPath();
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        Path blah[] = new Path[this.columns];
        int min = 0;
        this.energy = new double[this.columns][this.rows];
        for (int i = 0; i < this.columns; i++)
            for (int j = 0; j < this.rows; j++)
                energy[i][j] = energy(i, j);

        return findPath(true).curPath();
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null)
            throw new java.lang.IllegalArgumentException("Seam can't be null\n");
        if (this.rows <= 1)
            throw new java.lang.IllegalArgumentException("Width of picture equals one\n");
        if (seam.length == this.rows)
            throw new java.lang.IllegalArgumentException("Seam of wrong size\n");
        if (!validateSeam(seam, false))
            throw new java.lang.IllegalArgumentException("Probleam with the seam\n");

        int offset = 0;
        Picture temp = new Picture(columns, rows - 1);
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows - 1; j++) {
                if (seam[i] == j)
                    offset = 1;
                temp.setRGB(i, j, image.getRGB(i, j + offset));
            }
            offset = 0;
        }

        this.image = temp;
        this.rows -= 1;
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (seam == null)
            throw new java.lang.IllegalArgumentException("Seam can't be null\n");
        if (this.columns <= 1)
            throw new java.lang.IllegalArgumentException("Height of picture equals one\n");
        if (seam.length == this.columns)
            throw new java.lang.IllegalArgumentException("Seam of wrong size\n");
        if (!validateSeam(seam, true))
            throw new java.lang.IllegalArgumentException("Probleam with the seam\n");

        int offset = 0;
        Picture temp = new Picture(columns - 1, rows);
        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < columns - 1; i++) {
                if (seam[j] == i)
                    offset = 1;
                temp.setRGB(i, j, image.getRGB(i + offset, j));
            }
            offset = 0;
        }

        this.image = temp;
        this.columns -= 1;
    }

    private boolean validateSeam(int[] seam, boolean vertical) {
        if (vertical) {
            for (int i = 0; i < seam.length; i++) {
                if (seam[i] < 0 || seam[i] >= this.columns) return false;
                if (i == 0) continue;
                if (Math.abs(seam[i] - seam[i - 1]) > 2) return false;
            }
        }
        else {
            for (int j = 0; j < seam.length; j++) {
                if (seam[j] < 0 || seam[j] >= this.rows) return false;
                if (j == 0) continue;
                if (Math.abs(seam[j] - seam[j - 1]) > 2) return false;
            }
        }
        return true;
    }

    private double square(double x) {
        return x * x;
    }

    private int module(int x, int n) {
        if (x < 0)
            return n - 1;
        return x % n;
    }

    private Stack<Pixel> topological(Boolean vertical) {
        Pixel temp;
        HashMap<Pixel, Pixel> edgeTo = new HashMap<Pixel, Pixel>();
        Stack<Pixel> order = new Stack<Pixel>();
        if (!vertical) temp = new Pixel(-1, -1, vertical);
        else temp = new Pixel(-1, -1, vertical);
        topological(temp, edgeTo, order);
        return order;
    }

    private void topological(Pixel init, HashMap<Pixel, Pixel> edgeTo, Stack<Pixel> order) {
        Iterator<Pixel> bob;
        Pixel bobi;
        bob = init.neighboors();
        while (bob != null && bob.hasNext()) {
            bobi = bob.next();
            if (edgeTo.containsKey(bobi)) continue;
            edgeTo.put(bobi, init);
            topological(bobi, edgeTo, order);
        }
        if (init.x != -1 && init.y != -1)
            order.push(init);
        return;
    }

    private Path findPath(Boolean vertical) {
        Path path;
        Pixel bobi, end = null;
        Iterator<Pixel> bob;
        HashMap<Pixel, Double> costs = new HashMap<Pixel, Double>();
        HashMap<Pixel, Pixel> edgeTo = new HashMap<Pixel, Pixel>();
        Stack<Pixel> order = topological(vertical);

        if (!vertical) path = new Path(this.columns);
        else path = new Path(this.rows);

        for (Pixel temp : order) {
            if (!costs.containsKey(temp)) costs.put(temp, temp.cost);
            bob = temp.neighboors();
            if (bob == null && (end == null || costs.get(temp) < costs.get(end)))
                end = temp;

            while (bob != null && bob.hasNext()) {
                bobi = bob.next();
                if (costs.containsKey(bobi) && costs.get(bobi) < bobi.cost + costs.get(temp)) continue;
                costs.put(bobi, bobi.cost + costs.get(temp));
                edgeTo.put(bobi, temp);
            }
        }

        path.cost = costs.get(end);
        while (end != null) {
            if (vertical) path.addToPath(end.x);
            else path.addToPath(end.y);
            end = edgeTo.get(end);
        }
        return path;

    }

    //  unit testing (required)
    public static void main(String[] args) {

    }
}
