package dp;

import java.util.Arrays;

public class CountPaths {
    //  In this problem you are to count the number of ways of starting at the top-left comer
    // of a 2D array and getting to the bottom-right comer. All moves must either go right
    // or down. For example, we show three waysin a 5 X 5 2D array in Figure 17.5. (As we
    // will see, there are a total of 70 possible waysfor this example.)
    // Solution:
    //   at each step we can go down(i+1,j) or right(i,j+1)
    //   as soon as i == m && j == n hit
    //
    //   dp(i,j) = 1 + dp(i+1,j) + dp(j,i+1)
    //
    // Now lets analyze dp table
    // m = 2 n = 2, total=3
    //    0  1
    // 0  1  1
    // 1  1 0
    //
    //   0 1 2
    // 0 1 1 1
    // 1 1 2 3
    // 2 1 3 6
    ///
    //   * * *
    //       * x2
    //       *
    //
    //   * *
    //     * *  x2
    //       *
    //
    //   * *
    //     *    x2
    //     * *

    public static int countPaths(int i, int j) {
        if (i < 1 || j < 1) return 0;
        if (i == 1 && j == 1) return 1;
        return countPaths(i - 1, j) + countPaths(i, j - 1);
    }

    public static int countPathsIter(int m, int n) {
        if (m == 1 || n == 1) return 1;

        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) dp[i][j] = 1;
                else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(countPathsIter(10, 2));
//        for (int i = 1; i < 5; i++) {
//            System.out.printf(" ======== %s ======= \n", i);
//            System.out.println(countPaths(i, i));
//            System.out.println(countPathsIter(i, i));
//            System.out.printf(" ======== ======= \n");
//        }
    }

}
