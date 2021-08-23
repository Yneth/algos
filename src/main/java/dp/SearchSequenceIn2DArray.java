package dp;

import java.util.*;

public class SearchSequenceIn2DArray {
    //  Suppose you are given a 2D array of integers(the "grid"), and a IDarray of integers
    // (the "pattern"). We say the pattern occursin the grid if itispossible to startfromsome
    // entry in the grid and traverse adjacent entries in the order specified by the pattern
    // till all entries in the pattern have been visited. The entries adjacent to an entry are
    // the ones directly above, below, to the left, and to the right, assuming they exist. For
    // example, the entries adjacent to (3,4) are (3,3), (3,5), (4,4) and (5,4). It is acceptable
    // to visit an entry in the grid more than once.
    //   As an example, if the grid is
    // 1 2 3
    // 3 4 5
    // 5 6 7
    //   and the pattern is(1,3, 4,6), then the pattern occurs in the grid—consider the entries
    // ((0,0),(1, 0),(1,1),(2, 1)}. However, (1, 2, 3, 4} does not occur in the grid.
    //   Write a program that takes as arguments a 2D array and a ID array, and checks
    // whether the ID array occursin the 2D array.
    //
    // Assumptions:
    // - we are looking for target values as adjacent in the 2d grid
    //   adjacent is either to the left right or down or top
    //
    //   1 2 3
    //   4 5 6     (5,2,3,6)   5 ^ 2 > 3 \/ 6
    //   7 8 9
    //
    // - So at each step we can go 4 directions AND should iterate pattern by one
    //  dp(i,j,k) = | if target[k] == grid[i][j] -> (dp(i + 1, j, k - 1),
    //                                               dp(i, j + 1, k - 1),
    //                                               dp(i - 1, j, k - 1),
    //                                               dp(i, j - 1, k - 1))
    //              |
    //              | else                       ->  dp(i + 1, j, k)


    // Lets imagine a graph
    //

    private static <T> boolean isComplete(List<T> list, int size) {
        if (list != null) {
            System.out.println(list.size());
        }
        return list != EMPTY && list.size() == size;
    }

    private static <T> List<List<T>> withAll(List<List<T>> l, List<List<T>> val) {
        l.addAll(val);
        return l;
    }

    private static final int[][] DIRS = new int[][] {
        {-1, 0},
        {1, 0},
        {0, -1},
        {0, 1}
    };

    private static final List<List<Integer>> EMPTY = new ArrayList<>();

    private static List<List<Integer>> find(int[][] grid,
                                            int i, int j,
                                            int[] pattern,
                                            int k,
                                            List[][] cache) {
        if (i < 0 || i >= grid.length) return EMPTY;
        if (j < 0 || j >= grid[0].length) return EMPTY;
        if (k == pattern.length) return EMPTY;

        if (cache[i][j] != null) return cache[i][j];

        if (grid[i][j] == pattern[k]) {
            List<List<Integer>> curr = new ArrayList<>();
            curr.add(Arrays.asList(i, j));

            if (k == pattern.length - 1) {
                cache[i][j] = curr;
                return curr;
            }

            for (int[] dir : DIRS) {
                List<List<Integer>> res = find(grid, i + dir[0], j + dir[1], pattern, k + 1, cache);
                cache[i][j] = withAll(res, curr);
                if (isComplete(res, pattern.length - k - 1)) return withAll(res, curr);
            }
        }

        int nextCol = j == grid[0].length - 1 ? 0 : j + 1;
        int nextRow = j == grid[0].length - 1 ? i + 1 : i;
        return find(grid, nextRow, nextCol, pattern, k, cache);
    }

    public static List<List<Integer>> find(int[][] grid, int[] pattern) {
        return find(grid, 0, 0, pattern, 0, new ArrayList[grid.length][grid[0].length]);
    }

    public static void main(String[] args) {
        System.out.println(find(new int[][]{
            {1, 2, 3},
            {6, 0, 6},
            {3, 2, 9}
        }, new int[]{5, 2, 3, 6}));
    }
}
