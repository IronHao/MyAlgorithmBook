import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;

public class Board {
    private int[][] map;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles)
    {
        map = new int[tiles.length][tiles[0].length];
        for (int i = 0; i < tiles.length; i++)
            for (int j = 0; j < tiles[0].length; j++)
                map[i][j] = tiles[i][j];
    }
                                           
    // string representation of this board
    public String toString()
    {
        String ans = "";
        ans += (String.valueOf(dimension()) + "\n");
        for (int i = 0; i < dimension(); i++)
        {
            for (int j = 0; j < dimension(); j++)
            {
                ans += (" " + String.valueOf(map[i][j]) + " ");
            }
            ans += "\n";
        }
        return ans;
    }

    // board dimension n
    public int dimension() { return map.length; }

    // number of tiles out of place
    public int hamming()
    {
        int ans = 0;
        for (int i = 0; i < dimension(); i++)
        {
            for (int j = 0; j < dimension(); j++)
            {
                if (index(i, j) == dimension()*dimension())
                {
                    if (map[i][j] != 0)
                        ans++;
                }
                else if (map[i][j] != index(i, j))
                    ans++;
            }
        }
        return ans;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan()
    {
        int ans = 0;
        for (int i = 0; i < dimension(); i++)
        {
            for (int j = 0; j < dimension(); j++)
            {
                if (map[i][j] == 0)
                {
                    ans += Math.abs(dimension() - 1 - i);
                    ans += Math.abs(dimension() - (j+1));
                }
                else
                {
                    ans += Math.abs((map[i][j]-1) / dimension() - i); // The gap in rows
                    ans += Math.abs((map[i][j]-1) % dimension() - j); // The gap in columns
                }
                
            }
        }
        return ans;
    }

    // is this board the goal board?
    public boolean isGoal()
    {
        for (int i = 0; i < dimension(); i++)
        {
            for (int j = 0; j < dimension(); j++)
            {
                if (map[i][j] != 0 && map[i][j] != index(i, j))
                    return false;
            }
        }
        return true;
    }

    // does this board equal y?
    public boolean equals(Object y)
    {
        if (!(y instanceof Board))
            return false;
        Board b = (Board)y;
        if (dimension() != b.dimension())
            return false;
        for (int i = 0; i < dimension(); i++)
        {
            for (int j = 0; j < dimension(); j++)
            {
                if (map[i][j] != b.map[i][j])
                    return false;
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() { return new NeighborIterable(); }

    private class NeighborIterable implements Iterable<Board>
    {
        public Iterator<Board> iterator() { return new MyIterator(); }

        public class MyIterator implements Iterator<Board>
        {
            int blankRow, blankCol;
            int[] directions;    // 1-top, 2-down, 3-left, 4-right
            int curIndex;

            MyIterator()
            {
                boolean hasFindBlank = false;
                for (int i = 0; i < dimension(); i++)
                {
                    for (int j = 0; j < dimension(); j++)
                    {
                        if (map[i][j] == 0)
                        {
                            blankRow = i;
                            blankCol = j;
                            hasFindBlank = true;
                        }
                        if (hasFindBlank)
                            break;
                    }
                    if (hasFindBlank)
                        break;
                }
                // find all possible neighbor depending on the location of the blank square
                if (blankRow == 0 && blankCol == 0)
                    directions = new int[]{2, 4};
                else if (blankRow == 0 && blankCol == map.length-1)
                    directions = new int[]{2, 3};
                else if (blankRow == map.length-1 && blankCol == 0)
                    directions = new int[]{1, 4};
                else if (blankRow == map.length-1 && blankCol == map.length-1)
                    directions = new int[]{1, 3};
                else if (blankRow == 0)
                    directions = new int[]{2, 3, 4};
                else if (blankRow == map.length-1)
                    directions = new int[]{1, 3, 4};
                else if (blankCol == 0)
                    directions = new int[]{1, 2, 4};
                else if (blankCol == map.length-1)
                    directions = new int[]{1, 2, 3};
                else
                    directions = new int[]{1, 2, 3, 4};
                
                curIndex = 0;
            }

            public boolean hasNext() { return curIndex < directions.length; }

            public void remove() { throw new UnsupportedOperationException(); }

            public Board next()
            {
                Board ans = null;
                switch(directions[curIndex])
                {
                    // Top
                    case 1:
                        ans = swap(blankRow, blankCol, blankRow-1, blankCol);
                        break;
                    // Down
                    case 2:
                        ans = swap(blankRow, blankCol, blankRow+1, blankCol);
                        break;
                    // Left
                    case 3:
                        ans = swap(blankRow, blankCol, blankRow, blankCol-1);
                        break;
                    // Right
                    case 4:
                        ans = swap(blankRow, blankCol, blankRow, blankCol+1);
                        break;
                }
                curIndex++;
                return ans;
            }
        }
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin()
    {
        int row1 = 0;
        int col1 = 0;
        int row2 = 0;
        int col2 = 1;
        if (map[row1][col1] == 0)
            row1 = 1;
        else if (map[row2][col2] == 0)
            row2 = 1;
        return swap(row1, col1, row2, col2);
    }

    // Convert 2D index to 1D index. 2D range from 0 to dimension()-1, 1D range from 1 to dimension()^2
    private int index(int row, int col) { return row*dimension() + col + 1; }

    // Copy and return the map data
    private int[][] copyMap()
    {
        int[][] ans = new int[dimension()][dimension()];
        for (int i = 0; i < dimension(); i++)
            for (int j = 0; j < dimension(); j++)
                ans[i][j] = map[i][j];
        return ans;
    }

    // swap 'map[row1][col1]' with 'map[row2][col2]' and return the new Board with swapped map
    private Board swap(int row1, int col1, int row2, int col2)
    {
        int[][] newMap = copyMap();
        int temp = newMap[row1][col1];
        newMap[row1][col1] = newMap[row2][col2];
        newMap[row2][col2] = temp;
        return new Board(newMap);
    }

    // unit testing (not graded)
    public static void main(String[] args)
    {
        Board b = new Board(new int[][]{{1,2,3}, {4,5,6}, {7,8,0}});
        StdOut.println(b.isGoal());
        StdOut.println(b.hamming());
        StdOut.println(b.manhattan());
        StdOut.println(b.dimension());
        StdOut.print(b.toString());
        StdOut.print(b.twin().toString());
        StdOut.println(b.equals(b.twin()));
        for(Board tempB : b.neighbors())
        {
            StdOut.print(tempB);
        }
    }
}
