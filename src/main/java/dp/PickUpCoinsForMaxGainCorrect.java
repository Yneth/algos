package dp;

import java.util.*;

public class PickUpCoinsForMaxGainCorrect {
    //  In the pick-up-coins game, an even number of coins are placed in a line, as in Fig¬
    // ure 17.11. Two players take turns at choosing one coin each—they can only choose
    // from the two coins at the ends of the line. The game ends when all the coins have
    // been picked up. The player whose coins have the higher total value wins. A player
    // cannot pass his turn.
    //  Design an efficient algorithm for computing the maximum total value for the starting
    // player in the pick-up-coins game.
    // Assumptions:
    //  - array of coins with size  2n
    //  - return max gain
    //
    // Example:
    //  1, 10, 30, 10, 5
    //  1       2   1  2
    //
    // The problem is that we need some how to leverage
    // plays of both players, and pick the max of the mins.
    // Why mins? We are not interested in the greedy solution
    // it will not leverage second player moves
    //
    // Thus solution would be to pick max of playerA moves and playerB moves
    //  dp[i,j] = max(
    //    min(dp[i+1,j-1],dp[i+2,j]),
    //    min(dp[i+1,j-1],dp[i,j-2])
    //  )
    //
    // lets analyze graph of subproblems
    //  at each step we move either
    //  (down2,left2,ld1)
    // min(ld1, down2) min(ld1,left2)
    // in bottom up we will move
    //   up2,right2,ur1
    // in bottom up we need to work on all possible cases up to general subproblem


    public static int find(int[] coins, int a, int b, int[][] cache) {
        if (a > b) {
            return 0;
        }
        System.out.printf("(%s,%s)\n", a, b);
        if (cache[a][b] == 0) {
            int playerA = coins[a] + Math.min(
                find(coins, a + 1, b - 1, cache),
                find(coins, a + 2, b, cache)
            );
            int playerB = coins[b] + Math.min(
                find(coins, a + 1, b - 1, cache),
                find(coins, a, b - 2, cache)
            );
            cache[a][b] = Math.max(playerA, playerB);
        }
        return cache[a][b];
    }

    public static int find(int[] coins) {
        int[][] dp = new int[coins.length][coins.length];
        int res =  find(coins, 0, coins.length - 1, dp);
        Arrays.stream(dp)
            .map(Arrays::toString)
            .forEach(System.out::println);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(find(new int[]{7, 5, 25, 10, 1, 10}));
    }

}
