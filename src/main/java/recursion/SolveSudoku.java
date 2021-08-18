package recursion;

import java.util.*;

public class SolveSudoku {
    // Implement a Sudoku solver. See Problem 6.16 on Page 85 for a definition of Sudoku.
    //
    // Solution:
    //   start at (0,0)
    //     iterate from 0 to 9
    //     skip non empty cells
    //       try all possible values for empty cells
    //       if value is correct, recurse
    //
    // Test:
    // 0 0 0 1 3 2
    // I am lazy to write a test for this

    private static boolean solve(int i, int j, List<List<Integer>> partialSolution) {
        if (i == partialSolution.size()) {
            i = 0;
            if (++j == partialSolution.get(0).size()) {
                return true;
            }
        }
        if (partialSolution.get(i).get(j) != 0) {
            return solve(i, j + 1, partialSolution);
        }
        for (int val = 1; val <= partialSolution.size(); val++) {
            if (isValidValue(partialSolution, i, j, val)) {
                partialSolution.get(i).set(j, val);
                if (solve(i + 1, j, partialSolution)) {
                    return true;
                }
            }
        }
        partialSolution.get(i).set(j, 0);
        return false;
    }

    private static boolean isValidValue(List<List<Integer>> partialSolution, int x, int y, int val) {
        for (int j = 0; j < partialSolution.size(); j++) {
            if (partialSolution.get(x).get(j) == val) {
                return false;
            }
        }
        for (int i = 0; i < partialSolution.size(); i++) {
            if (partialSolution.get(i).get(y) == val) {
                return false;
            }
        }
        int size = (int) Math.sqrt(partialSolution.size());
        int I = x / size, J = y / size;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (partialSolution.get(I * size + i).get(J * size + j) == val) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean solve(List<List<Integer>> partialSolution) {
        return solve(0, 0, partialSolution);
    }

    public static void main(String[] args) {
        List<List<Integer>> sudoku = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            sudoku.add(new ArrayList<>());
            for (int j = 0; j < 9; j++) {
                sudoku.get(i).add(0);
            }
        }
        System.out.println(sudoku);
        System.out.println(solve( sudoku ));
        System.out.println(sudoku);
    }
}
