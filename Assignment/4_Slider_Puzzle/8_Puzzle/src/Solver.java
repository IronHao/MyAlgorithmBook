import java.util.Comparator;
import java.util.Iterator;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
    private MinPQ<GameNode> priorityQ;
    private GameNode curNode;
    private MinPQ<GameNode> priorityQ_twin;
    private GameNode curNode_twin;

    private class GameNode implements Comparable<GameNode>
    {
        private Board board; 
        private int moves;
        private int priority;
        private GameNode prevNode;

        public GameNode(Board bd, GameNode pN)
        {
            board = bd;
            prevNode = pN;
            if (prevNode == null)
                moves = 0;
            else
                moves = prevNode.moves() + 1;
            priority = board.manhattan() + moves;
            
        }

        public int compareTo(GameNode other)
        {
            if (priority() == other.priority())
                return 0;
            else if (priority() < other.priority())
                return -1;
            else
                return 1;
        }

        public Board board() { return board; }
        public int priority() { return priority; }
        public int moves() { return moves; }
        public GameNode prevNode() { return prevNode; }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial)
    {
        priorityQ = new MinPQ<>();
        priorityQ.insert(new GameNode(initial, null));
        priorityQ_twin = new MinPQ<>();
        priorityQ_twin.insert(new GameNode(initial.twin(), null));
        while (!priorityQ.isEmpty() && !priorityQ_twin.isEmpty())
        {
            curNode = priorityQ.delMin();
            if (curNode.board().isGoal())
                break;
            for(Board bd : curNode.board.neighbors())
                if (curNode.prevNode() == null || !bd.equals(curNode.prevNode().board()))
                    priorityQ.insert(new GameNode(bd, curNode));
            // The twin
            curNode_twin = priorityQ_twin.delMin();
            if (curNode_twin.board().isGoal())
                break;
            for(Board bd : curNode_twin.board.neighbors())
                if (curNode_twin.prevNode() == null || !bd.equals(curNode_twin.prevNode().board()))
                    priorityQ_twin.insert(new GameNode(bd, curNode_twin));
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() { return curNode.board().isGoal(); }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() { return isSolvable() ? curNode.moves() : -1; }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() { return isSolvable() ? new BoardSequenceIterable() : null; }

    private class BoardSequenceIterable implements Iterable<Board>
    {
        public Iterator<Board> iterator() { return new MyIterator(); }

        public class MyIterator implements Iterator<Board>
        {
            Stack<GameNode> s;
            MyIterator()
            {
                GameNode cur = curNode;
                s = new Stack<GameNode>();
                while (cur != null)
                {
                    s.push(cur);
                    cur = cur.prevNode();
                }
            }

            public boolean hasNext() { return !s.isEmpty(); }

            public void remove() { throw new UnsupportedOperationException(); }

            public Board next() { return s.pop().board(); }
        }
    }

    // test client (see below) 
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);
    
        // solve the puzzle
        Solver solver = new Solver(initial);
    
        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
