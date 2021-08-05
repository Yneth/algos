package search;

import java.util.Arrays;
import java.util.List;

public class Search2DSortedArray {
    // Call a 2D array sorted if its rows and its columns are non decreasing. See Figure 12.3
    // on the facing page for an example of a 2D sorted array.
    // Design an algorithm that takes a 2D sorted array and a number and checks whether
    // that number appears in the array. For example, if the input is the 2D sorted array in
    // Figure12.3 on the next page, and the number is 7, your algorithm should return false;
    // if the number is 8, your algorithm should return true.
    // Example:
    //  -1,2 ,5 ,7
    //   3,4 ,6 ,8
    //   5,8 ,9 ,10
    //   9,10,11,20
    //
    //  look for 5
    //  5 < 7 eliminate col 3
    //  5 == 5 return 0 2
    //
    //  target=3
    //  3 < 7 -c3
    //  3 < 5 -c2
    //  3 > 2  3 > -1
    //  3 < 4 -c1
    //  3 == 3 return 1 0

    // Solution:
    //   for each row
    //      check target with the last possible col
    //      if it is higher than target
    //       dec maxCol
    //      else
    //       check all cols of current row
    //


    public static List<Integer> find(int[][] m, int t) {
        int col = m[0].length - 1;
        for (int i = 0; i < m.length; i++) {
            for (int j = col; j >= 0; j--) {
                int val = m[i][j];
                if (val == t) return Arrays.asList(i, j);
                else if (val > t) col--;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[][] m = new int[][]{
            {-1, 2, 5, 7},
            {3, 3, 6, 8},
            {5, 8, 9, 10},
            {9, 10, 11, 20}
        };
        System.out.println(find(m, -1)); //0,0
        System.out.println(find(m, 2)); //0,1
        System.out.println(find(m, 4)); // null
        System.out.println(find(m, 3)); //1,1
        System.out.println(find(m, 6)); //1,2
        System.out.println(find(m, 20)); //3,3
        System.out.println(find(m, 9)); //2,2
        System.out.println(find(m, 30)); //null
    }
}
