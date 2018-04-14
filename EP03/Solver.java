import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;


public class Solver {
    private int mim_moves;
    private Node current;

    private class Node implements Comparable<Node> {
        private int priority;
        private int moves;
        private Board board;
        private Node prev;
        private Node(Board new_board, Node previos, int mv) {
            this.priority = new_board.manhattan() + mv;
            this.moves = mv;
            this.board = new_board;
            this.prev = previos;
        }

        @Override
        public int compareTo(Node otherNode) {
            if (this.priority == otherNode.priority)
                return 0;
            else if (this.priority < otherNode.priority)
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

        mim_moves = 0;

        MinPQ<Node> frontier = new MinPQ<Node>();
        this.current = new Node(initial, null, 0);
        while (!this.current.board.isGoal()) {
            for (Board a : this.current.board.neighbors()) {
                if (current.prev != null) {
                    if (!a.equals(current.prev.board)) {
                        frontier.insert(new Node(a, this.current, this.current.moves + 1));
                    }
                }
                else {
                    frontier.insert(new Node(a, this.current, this.current.moves + 1));
                }
            }
            this.current = frontier.delMin();
        }
    }

    // min number of moves to solve initial board
    public int moves() {
        Node curr = this.current;
        while (curr != null) {
            mim_moves += 1;
            curr = curr.prev;
        }
        mim_moves -= 1;
        return mim_moves;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        Stack<Board> stack = new Stack<Board>();
        Node curr = this.current;
        while (curr != null) {
            stack.push(curr.board);
            curr = curr.prev;
        }
        return stack;
    }

    // test client (see below)
    public static void main(String[] args) {
        int[][] bob = {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
        Board bobs = new Board(bob);
        Solver boos = new Solver(bobs);
        for (Board a : boos.solution()) {
            StdOut.print(a.toString());
        }
    }
}
