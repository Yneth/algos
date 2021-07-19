package arrays;

import java.util.*;

public class CheckSudoku {
    // timer started after 2 mins I guess

    // problem:
    // check if sudoku board is correct
    //
    // solution:
    // I already know the solution
    // actually there are two of them
    //
    // #1 three checks
    //    check horizontal rows
    //    check vertical rows
    //    check boxes
    // for each specific check init an integer with
    // value of (9 * 10) / 2 = 45
    // and decrement each value
    // if it is not equal to 0 at the end - fail
    //
    // in three steps
    // Time: O(3 n*m) ~= O(3 * 9 * 9) ~= O(1)
    // Space: O(1)
    //
    // #2 use map
    //   init set<str>
    //   run one loop over whole matrix
    //      set.add["i_" + v]
    //      set.add["j_" + v]
    //      int boxNum = Math.floor(i / 3) + Math.floor(j / 3);
    //      set.add["" + (i * 3) + j + "" + v]
    //
    // boxNum test:
    //   0 0 -> 0 +0 = 0
    //   2 2 -> 2/3 + 2/3 = 0
    //   3 3 -> 3/3 + 3/3 = 2
    //   5 5 -> 5/3 +5/3 = 2
    //   5 0 -> 5/3 + 0/3 = 1

    // Space: O(3*3) ~= O(1)
    // Time: O(3*3) ~= O(1)

    public boolean check(int[][] a) {
        Set<String> checks = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int val = a[i][j];
                String rowKey = "i_" + val;
                String colKey = "j_" + val;

                int box = (int) (Math.floor(i / 3) + Math.floor(j / 3));
                String boxKey = "b_" + box + "_" + val;

                if (checks.contains(rowKey)) return false;
                else checks.add(rowKey);
                if (checks.contains(colKey)) return false;
                else checks.add(colKey);
                if (checks.contains(boxKey)) return false;
                else checks.add(boxKey);
            }
        }
        return true;
    }


    // Total time 23 minutes
    // took too much time to understand index mapping for boxes
    // failed with choosing of map
    // instead of the set
    // BAD 2/5
}
