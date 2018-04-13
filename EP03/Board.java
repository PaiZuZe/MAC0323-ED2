import java.lang.Math;
import java.util.Iterator;

public class Board {
    private int n, i0, j0;
    private int[][] board;

    private int single_manhathan(int x, int y) {
        int x_value = this.board[x][y]%this.n;
        int y_value = this.board[x][y]/this.n;
        return Math.abs(x - x_value) + Math.abs(y - y_value);
    }

    private int n_inversions() {
        int inversions = 0;
        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.n; j++)
                for (int k = i; k < this.n; k++)
                    for (int l = j + 1; j < this.n; l++)
                        if (this.board[i][j] > this.board[k][l])
                            inversions += 1;

        return inversions;
    }

    private class BoardIterator implements Iterator<Board> {
        private Board[] neighbors = new Board[4];
        private int[][] copy;
        private int num_other_boards = 0, nxt = 0;

        private int[][] swap(int[][] board, int oi, int oj, int ni, int nj) {
            int temp = board[oi][oj];
            board[oi][oj] = board[ni][nj];
            board[ni][nj] = temp;
            return board;
        }

        public BoardIterator() {
            copy = new int[n][n];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    copy[i][j] = board[i][j];

            if (i0 != 0) {
                neighbors[num_other_boards++] = new Board(swap(copy, i0, j0, i0 - 1, j0));
                copy = swap(copy, i0, j0, i0 - 1, j0);
            }
            if (i0 != n -1) {
                neighbors[num_other_boards++] = new Board(swap(copy, i0, j0, i0 + 1, j0));
                copy = swap(copy, i0, j0, i0 + 1, j0);
            }
            if (j0 != 0) {
                neighbors[num_other_boards++] = new Board(swap(copy, i0, j0, i0, j0 - 1));
                copy = swap(copy, i0, j0, i0, j0 - 1);
            }
            if (j0 != n -1) {
                neighbors[num_other_boards++] = new Board(swap(copy, i0, j0, i0, j0 + 1));
                copy = swap(copy, i0, j0, i0, j0 + 1);
            }
        }

        public boolean hasNext() {
            return num_other_boards != nxt;
        }

        public Board next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException("Board has no neighbors left.\n");
            return neighbors[nxt++];
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException("Invalid operation.\n");
        }
    }
    class BoardIterable implements Iterable<Board> {
            @Override
            public Iterator<Board> iterator() {
                return new BoardIterator();
            }
    }


    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.n = tiles[0].length;
        board = new int[this.n][this.n];
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                this.board[i][j] = tiles[i][j];
                if (tiles[i][j] == 0) {
                    i0 = i;
                    j0 = j;
                }
            }
        }
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
        if (0 <= row && row < this.n && 0 <= col && row < this.n)
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
                if (i * this.n + j + 1 != this.board[i][j] && this.board[i][j] != 0)
                    out_tiles += 1;
        return out_tiles;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int tot_manha = 0;
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                if (this.board[i][j] == 0)
                    continue;
                tot_manha += single_manhathan(i, j);
            }
        }
        return tot_manha;
    }

    // is this board the goal board?
    public boolean isGoal() {
        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.n; j++)
                if (i * this.n + j + 1 != this.board[i][j])
                    return false;
        return true;
    }

     // does this board equal y?
    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null) return false;
        if (getClass() != y.getClass()) return false;
        Board other_board = (Board) y;
        return (this.n == other_board.n) && (this.board.equals(other_board.board));

    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return new BoardIterable();
    }

    // is this board solvable?
    public boolean isSolvable() {
        int inver = n_inversions();
        if (this.n % 2 == 0 && inver + this.i0 % 2 == 1)
            return true;
        if (this.n % 2 == 1 && inver % 2 == 0)
            return true;
        return false;
    }

    // unit testing (required)
    public static void main(String[] args) {

    }
}
