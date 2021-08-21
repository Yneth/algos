package dp;

import java.util.*;

public class ScoreCombinations {
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
    // Example:
    // 9
    // 2+7
    // 3+3+3
    // 7+2
    //
    // recursive(n)
    //   if n < 0: return 0
    //   if n == 0: return 1
    //   return recursive(n - 2) + recursive(n - 3) + recursive(n - 7)
    //
    // table:
    // init first row populated with 9
    // at each iteration do
    //   iterate over the array
    //     if prev[i] == 0: continue
    //     populate new with prev[i] - 2,3,7
    // 9
    // 7       6        2
    // 5 4 0   4 3 -1   0 -1 -5
    // continue up until the whole row would contain only x >= 0

    public static int countScoreCombinations(int score) {
        final int[] scoreTypes = new int[] {2, 3, 7};

        List<Integer> dp = new ArrayList<>();
        dp.add(score);

        int result = 0;
        while (!dp.isEmpty()) {
            List<Integer> next = new ArrayList<>();
            for (int i = 0; i < dp.size(); i++) {
                for (int j = 0; j < scoreTypes.length; j++) {
                    int val = dp.get(i) - scoreTypes[j];
                    if (val == 0) {
                        result++;
                    } else if (val > 0) {
                        next.add(val);
                    }
                }
            }
            dp = next;
        }
        return result;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 25; i++) {
            System.out.println(i + ":   " + countScoreCombinations(i));
        }
    }

}
