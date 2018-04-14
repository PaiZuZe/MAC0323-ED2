
public class Solver {
    private int mim_moves;

    private class Node implements Comparable<Node> {
        private Board board;
        private Node(Board boar) {
            this.board = boar;
        }
        @Override
        public int compareTo(Node otherNode) {
            if (this.board.manhattan() == otherNode.board.manhattan())
                return 0;
            else if (this.board.manhattan() < otherNode.board.manhattan())
                return -1;
            else
                return 1;
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null)
            throw new java.lang.IllegalArgumentException("Board can't be null.\n");
        if (!initial.isSolvable())
            throw new java.lang.IllegalArgumentException("Board can't be solved.\n");

    }

    // min number of moves to solve initial board
    public int moves() {
        return mim_moves;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        return null;
    }

    // test client (see below)
    public static void main(String[] args) {

    }
}
