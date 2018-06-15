import edu.princeton.cs.algs4.IndexMinPQ<Pixel>;

public class SeamCarver {
    private Picture image;
    private double energy[][];
    private int n;
    private int m;

    private class Pixel {
        int this.x;
        int this.y;

        public Pixel(int x, int y) {
            this.x = x;
            this.y = y;
            return;
        }
    }

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        this.image = picture.clone();
        this.n = this.image.height();
        this.m = this.image.width();
    }

    // current picture
    public Picture picture() {
        return this.image;
    }

    // width of current picture
    public int width() {
        return this.n;
    }

    // height of current picture
    public int height() {
        return this.m;
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x < 0 || x >= this.n || y < 0 || y >= this.m)
            throw new java.lang.IllegalArgumentException("X or y out of range\n");
        double delX;
        double delY;
        Color cor1 = this.image.getRGB(module(x - 1, this.n), y);;
        Color cor2 = this.image.getRGB(module(x + 1, this.n), y);;
        Color cor3 = this.image.getRGB(x, module(y - 1, this.m));;
        Color cor4 = this.image.getRGB(x, module(y + 1, this.m));;
        delX = square(cor1.getRed() - cor2.getRed()) + square(cor1.getGreen() - cor2.getGreen()) + square(cor1.getBlue() - cor2.getBlue());
        delY = square(cor3.getRed() - cor4.getRed()) + square(cor3.getGreen() - cor4.getGreen()) + square(cor3.getBlue() - cor4.getBlue());
        return(sqrt(square(delX) + square(delY)));
    }
    /*O lance parece colocar em uma PQ com o  menor peso(valor da energia), usar Djikstra */
    /*Uma matriz conde calc todos as energ antes ta dentro do perf req*/
    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        this.energy = new double[this.n][this.m];
        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.m; j++)
                energy[i][j] = energy(i, j);
        for (int j = 0; j < this.m; j++) {

        }
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        this.energy = new double[this.n][this.m];
        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.m; j++)
                energy[i][j] = energy(i, j);
        for (int i = 0; i < this.n; i++) {

        }
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null)
            throw new java.lang.IllegalArgumentException("Seam can't be null\n");
        if (this.m <= 1)
            throw new java.lang.IllegalArgumentException("Width of picture equals one\n");
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (seam == null)
            throw new java.lang.IllegalArgumentException("Seam can't be null\n");
        if (this.n <= 1)
            throw new java.lang.IllegalArgumentException("Height of picture equals one\n");
    }

    private double square(double x) {
        return x * x;
    }

    private int module(int x, int n) {
        if (x < 0)
            return n - 1;
        return x % n;
    }

    //  unit testing (required)
    public static void main(String[] args) {

    }
}
