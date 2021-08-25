package dp;

import java.util.Arrays;
import java.util.List;

public class MinWeightPathInTriangle {
    //  A sequence of integer arrays in which the nth array consists of n entries naturally
    // corresponds to a triangle of numbers. See Figure 17.10 for an example.
    //  Define a path in the triangle to be a sequence of entries in the triangle in which
    // adjacent entries in the sequence correspond to entries that are adjacent in the triangle.
    // The path must start at the top, descend the triangle continuously, and end with an
    // entry on the bottom row. The weight of a path is the sum of the entries.
    //  Write a program that takes asinput a triangle of numbers and returns the weight of
    // a minimum weight path. For example, the minimum weight path for the number
    // triangle in Figure 17.10 isshown in bold face, and its weight is 15.
    //
    // Example:
    //      1
    //     2 2
    //    4 5  8
    //   4 5  8
    //  10 3 2 1 20
    //
    //  1 2 2 1 = 5
    //  in order to solve we need to calculate possible indicies on the next level
    //
    //  from level 3(2) to level 4(1)
    //  2 at index 2
    //  1 at index 3
    //   2 can go either 2 or 3
    // each cell at i can go i or i + 1
    //
    // Solution:
    //   dp[l, i, sum] = min(dp[l + 1, i, sum + curr], dp[l + 1, i + 1, sum + curr])
    //      L  N  S    = S
    //

    private static int find(List<List<Integer>> t, int l, int i, int sum) {
        if (l == t.size()) return sum;
        int nextSum = sum + t.get(l).get(i);
        return Math.min(
            find(t, l + 1, i, nextSum),
            find(t, l + 1, i + 1, nextSum)
        );
    }

    public static int findIter(List<List<Integer>> triangle) {
        int[] dp = new int[1];
        dp[0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i++) {
            List<Integer> level = triangle.get(i);
            int[] next = new int[level.size()];
            for (int j = 0; j < level.size(); j++) {
                if (j == 0) {
                    next[j] = level.get(j) + dp[j];
                } else if (j == dp.length) {
                    next[j] = level.get(j) + dp[j - 1];
                } else {
                    next[j] = Math.min(level.get(j) + dp[j], level.get(j) + dp[j - 1]);
                }
            }
            dp = next;
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < dp.length; i++) {
            result = Math.min(result, dp[i]);
        }
        return result;
    }

    public static int find(List<List<Integer>> triangle) {
        return find(triangle, 0, 0, 0);
    }

    public static void main(String[] args) {
        System.out.println(find(
            Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2, 0),
                Arrays.asList(20, 0, 2),
                Arrays.asList(10, 100, 91, 1)
            )
        ));
    }

}
