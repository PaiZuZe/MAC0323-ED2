import java.lang.Math;

public class Board {
    private int n;
    private int[][] board;

    private int single_manhathan(int x, int y) {
        int x_value = this.board[x][y]%this.n;
        int y_value = this.board[x][y]/this.n;
        return math.abs(x - x_value) + math.abs(y - y_value);
    }

    private class BoardIterator implements Iterator<Board> {
        private Node current;

        public DequeIterator() {
            this.current = first;
        }

        public boolean hasNext() {
            return this.current != null;
        }
        public Item next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException("Board has no neighbors left.\n");
            Node temp = this.current;
            this.current = this.current.next;
            return temp.data;
        }
        public void remove() {
            throw new java.lang.UnsupportedOperationException("Invalid operation.\n");
        }
    }


    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.n = tiles[0].lenth;
        board = new int[this.n][this.n];
        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.n; j++)
                thos.board[i][j] = tiles[i][j];
        return;
    }

    // string representation of this board
    public String toString() {
        String s_matrix = this.n + "\n";
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++)
                s_matrix += " " + this.board[i][j];
            s_matrix += "\n";
        }
        return s_matrix;
    }

    // tile at (row, col) or 0 if blank
    public int tileAt(int row, int col) {
        if (0 <= row < this.n or 0 <= col < this.n)
            throw new java.lang.IllegalArgumentException("col or row are not in the right range.\n");
        return this.board[row][col];
    }

    // board size n
    public int size() {
        return this.n;
    }

    // number of tiles out of place
    public int hamming() {
        int out_tiles = 0;
        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.n; j++)
                if (i * this.size + j + 1 != this.board[i][j] and this.board[i][j] != 0)
                    out_tiles += 1;
        return out_tiles;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int tot_manha = 0;
        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.n; j++)
                if (this.board[i][j] == 0)
                    continue;
                tot_manha += single_manhathan(i, j);
        return tot_manha;
    }

    // is this board the goal board?
    public boolean isGoal() {
        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.n; j++)
                if (i * this.size + j + 1 != this.board[i][j])
                    return false;
        return true;
    }

     // does this board equal y?
    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null) return false;
        if (getClass() != y.getClass()) return false;
        Board other_board = (Board) y;
        return Objects.equals(this.n, other_board.n) && Objects.equals(this.board, other_board.board);

    }

    // all neighboring boards
    public Iterable<Board> neighbors() {

    }

    // is this board solvable?
    public boolean isSolvable() {

    }

    // unit testing (required)
    public static void main(String[] args) {

    }
}
