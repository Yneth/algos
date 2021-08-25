package dp;

import java.util.*;

public class PickUpCoinsForMaxGain {
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
    //  Need a way to leverage two players
    //  lets represent each play as an integer
    //   at each step of the game
    //     both players can go two ways of playing
    //     we are actually no interested in having two integers
    //     as they will overlap
    //   at each step we can take only one coin but need to remove the other one
    // so the formula will look like
    //
    //  dp[i,j] = max(coins[i] + dp[i + 1, j - 1], coins[j] + dp[i + 1, j - 1])
    //
    //  at each step we only need the sums from previous choices

    public static int find(int[] coins) {
        List<Integer> prevChoices = new ArrayList<>();
        prevChoices.add(coins[0]);
        prevChoices.add(coins[coins.length - 1]);
        int left = 1, right = coins.length - 2;
        while (left < right) {
            List<Integer> currChoices = new ArrayList<>();
            for (int i = 0; i < prevChoices.size(); i++) {
                int choice = prevChoices.get(i);
                currChoices.add(choice + Math.max(coins[left], coins[right]));
                currChoices.add(choice + Math.max(coins[left], coins[right]));
            }
            left++;
            right--;
            System.out.println(currChoices);
            prevChoices = currChoices;
        }
        return Collections.max(prevChoices);
    }

    public static void main(String[] args) {
        System.out.println(find(new int[] {5, 25, 10, 1}));
    }

}
