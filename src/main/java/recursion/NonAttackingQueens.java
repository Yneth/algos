package recursion;

import java.util.*;

public class NonAttackingQueens {
    //  A nonattacking placement of queens is one in which no two queens are in the same
    // row, column, or diagonal. See Figure 16.3 for an example.
    //  Write a program which returns all distinct nonattacking placements of n queens on
    // an nxn chessboard, where n is an input to the program.
    //
    // lets say we have i,j limited by [0,n).
    // to generate all non-attacking positions
    // we could
    // - generate all positions
    //   filter out bad ones
    //
    // example:
    // n=4
    // generate the first q, she could be at three positions
    // 0 1 2 3
    // then next queen
    // - - q -
    // q - - -
    // - - - q
    // - q - -
    // we need to think only of diagonals and verticals
    // then next queen can only be seen at [i+1, [0, j - 2]] [i+1, [j + 2, n - 1]]
    //

    private static boolean isValid(List<Integer> positions) {
        int currRow = positions.size() - 1;
        for (int i = 0; i < currRow; i++) {
            int diff = Math.abs(positions.get(i) - positions.get(currRow));
            if (diff == 0 || diff == currRow - i)
                return false;
        }
        return true;
    }

    private static List<List<Integer>> nQueens(int n, List<Integer> positions, List<List<Integer>> result) {
        if (positions.size() == n) {
            result.add(new ArrayList<>(positions));
        } else {
            for (int i = 0; i < n; i++) {
                positions.add(i);
                if (isValid(positions)) {
                    nQueens(n, positions, result);
                }
                positions.remove(positions.size() - 1);
            }
        }
        return result;
    }

    public static List<List<Integer>> nQueens(int n) {
        return nQueens(n, new ArrayList<>(), new ArrayList<>());
    }

    public static void main(String[] args) {
        System.out.println(nQueens(4));
    }
}
