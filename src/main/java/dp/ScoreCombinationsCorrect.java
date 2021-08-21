package dp;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class ScoreCombinationsCorrect {
    //  In an American football game, a play can lead to 2 points(safety), 3 points(field goal),
    // or 7 points (touchdown, assuming the extra point). Many different combinations of
    // 2, 3, and 7 point plays can make up a final score. For example, four combinations of
    // plays yield a score of 12:
    // •
    // 6 safeties(2x6 = 12),
    // •
    // 3 safeties and 2 field goals(2x3 + 3x2 = 12),
    // • 1safety,1 field goal and 1 touchdown (2xl + 3xl + 7xl = 12), and
    // • 4 field goals(3x4 = 12).
    //  Write a program that takes a final score and scoresfor individual plays, and returns
    // the number of combinations of plays that result in the finalscore
    //
    // Assumptions:
    //  - should not count duplicate combinations
    //
    // Solution:
    //   init a matrix of comibations for 2 for 2,3 and for 2,3,7
    //      first position is set to 1 always
    //      populate each column of row as a sum of
    //           combinations without this play [i - 1][j]
    //           + combinations with this play  [i][j - value]
    //
    // Example:
    //       12 11 10 9 8 7 6 5 4 3 2 1 0
    //2       1  0  1 0 1 0 1 0 1 0 1 0 1
    //2,3     1  0  1 1 1 1 2 1 2 2 2 2 3
    //2,3,7   1  0  1 1 1 1 2 2 2 3 3 3 4

    public static int countCombinations(int score, int[] plays) {
        int[][] combinations = new int[plays.length][score + 1];
        for (int i = 0; i < plays.length; i++) {
            combinations[i][0] = 1;
            for (int j = 1; j <= score; j++) {
                int play = plays[i];

                int withoutPlay = i - 1 >= 0 ? combinations[i - 1][j] : 0;
                int withPlay = j - play >= 0 ? combinations[i][j - play] : 0;

                combinations[i][j] = withoutPlay + withPlay;
            }
        }
        return combinations[plays.length - 1][score];
    }

    public static void main(String[] args) {
        for (int i = 0; i < 25; i++) {
            System.out.println(i + ":   " + countCombinations(i, new int[] {2,3,7}));
        }
    }

}
