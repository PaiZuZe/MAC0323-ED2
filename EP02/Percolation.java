import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private byte[][] matrix;
    private int num_opens;
    private int size;
    private WeightedQuickUnionUF UF;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new java.lang.IllegalArgumentException("Percolation: n must be larger than 0.\n");

        this.matrix = new byte[n][n];
        this.size = n;
        this.num_opens = n*n;
        this.llastOpened = -1;
        for (int i = 0; i < this.size; i++)
            for (int j = 0; j < this.size; j++)
                this.matrix[i][j] = 1;
        UF = new WeightedQuickUnionUF(this.num_opens);
        return;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row >= this.size || col >= this.size)
            throw new java.lang.IllegalArgumentException("open: Out of bounds.\n");

        this.matrix[row][col] = 0;
        this.num_opens -= 1;


        if (row -1 >= 0 && this.matrix[row - 1][col] == 0)
            UF.union((row - 1) * this.size + col , row * this.size + col);
        if (col + 1 < this.size && this.matrix[row][col + 1] == 0)
            UF.union(row * this.size + col + 1 , row * this.size + col);
        if (row + 1 < this.size && this.matrix[row + 1][col] == 0)
            UF.union((row + 1) * this.size + col , row * this.size + col);
        if (col - 1 >= 0  && this.matrix[row][col - 1] == 0)
            UF.union(row * this.size + col - 1 , row * this.size + col);

        return;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row >= this.size || col >= this.size)
            throw new java.lang.IllegalArgumentException("isOpen: Out of bounds.\n");

        return this.matrix[row][col] == 0;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row >= this.size || col >= this.size)
            throw new java.lang.IllegalArgumentException("isFull: Out of bounds.\n");

        return this.matrix[row][col] == 1;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.num_opens;
    }

    // does the system percolate?
    public boolean percolates() {
        for (i = 0; i < this.size; i++)
            for (j = 0; j < this.size; j++)
                if (isOpen(0, i))
                    if (UF.connected(i, (n-1)*n + j)
                        return true
                else
                    break;
    }

    // unit testing (required)
    public static void main(String[] args) {
        Percolation test = new Percolation(Integer.parseInt(args[0]));
        StdOut.print(test.isFull(0,0) + "\n");
        test.open(0,0);
        StdOut.print(test.isFull(0,0) + "\n");


    }
}
