import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdRandom;

public class Percolation {
    private byte[][] grid;
    private int num_opens;
    private int size;
    private WeightedQuickUnionUF UF;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new java.lang.IllegalArgumentException("Percolation: n must be larger than 0.\n");

        this.grid = new byte[n][n];
        this.size = n;
        this.num_opens = 0;

        for (int i = 0; i < this.size; i++)
            for (int j = 0; j < this.size; j++)
                this.grid[i][j] = 1;

        UF = new WeightedQuickUnionUF(n*n + 2);
        return;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row >= this.size || col >= this.size)
            throw new java.lang.IllegalArgumentException("open: Out of bounds.\n");

        //If it was already open, we do nothing.
        if (this.grid[row][col] == 0)
            return;

        this.grid[row][col] = 0;
        this.num_opens += 1;
        //Added two new parts to our UF, this way we can check if percolates in O(1).
        if (row == 0)
            UF.union(this.size * this.size, row * this.size + col);
        if (row == this.size - 1)
            UF.union(this.size * this.size + 1, row * this.size + col);

        if (row -1 >= 0 && this.grid[row - 1][col] == 0)
            UF.union((row - 1) * this.size + col , row * this.size + col);
        if (col + 1 < this.size && this.grid[row][col + 1] == 0)
            UF.union(row * this.size + col + 1 , row * this.size + col);
        if (row + 1 < this.size && this.grid[row + 1][col] == 0)
            UF.union((row + 1) * this.size + col , row * this.size + col);
        if (col - 1 >= 0  && this.grid[row][col - 1] == 0)
            UF.union(row * this.size + col - 1 , row * this.size + col);

        return;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row >= this.size || col >= this.size)
            throw new java.lang.IllegalArgumentException("isOpen: Out of bounds.\n");

        return this.grid[row][col] == 0;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row >= this.size || col >= this.size)
            throw new java.lang.IllegalArgumentException("isFull: Out of bounds.\n");

        return UF.connected(this.size * this.size, this.size * row + col);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.num_opens;
    }

    // does the system percolate?
    public boolean percolates() {
        return UF.connected(this.size * this.size, this.size * this.size + 1);
    }

    // unit testing (required)
    public static void main(String[] args) {
        Percolation test = new Percolation(Integer.parseInt(args[0]));

        StdOut.print("isFull: " + test.isFull(0,0) + "\n");
        StdOut.print("isOpen: " + test.isOpen(0,0) + "\n");

        test.open(0,0);

        StdOut.print("isFull: " + test.isFull(0,0) + "\n");
        StdOut.print("isOpen: " + test.isOpen(0,0) + "\n");
        while (!test.percolates())
            test.open(StdRandom.uniform(Integer.parseInt(args[0])), StdRandom.uniform(Integer.parseInt(args[0])));
        return;
    }
}
