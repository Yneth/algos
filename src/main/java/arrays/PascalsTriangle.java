package arrays;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {
    // show pascals triangle rows
    // Solution:
    //   lets' first visualize pascals' triangle as a matrix
    //   1
    //   1 4
    //   1 3 6
    //   1 2 3 4
    //   1 1 1 1 1
    // as we can see
    // each element is equal to sum of
    //    triangle(i, j - 1) + triangle(i - 1, j)
    //      where triangle(0, x) = 1
    //            triangle(y, 0) = 1
    //
    // to calculate specific row we can just do the following
    //    Given row index L
    //     res = []
    //     for i 0..L
    //       res.push(triangle(i, L - i))
    // Test:
    //   L = 2
    //  #0 t(0,2) = 1
    //  #1 t(1,1) = 2
    //  #2 t(2,0) = 1
    //
    //    L=3
    //  #0 t(0,3) = 1
    //  #1 t(1,2) = 3
    //  #1 t(2,1) = 3
    //  #1 t(3,0) = 1
    //
    // 11:22 minutes
    // Space: O(n)
    // Time: O(n 2^n)

    private int triangle(int i, int j) {
        if (i < 0 || j < 0) return 0;
        if (i == 0 || j == 0) return 1;
        return triangle(i - 1, j) + triangle(i, j - 1);
    }

    public List<Integer> solve(int l) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i <= l; i++) {
            result.add(triangle(i, l - i));
        }
        return result;
    }


    // Solution:
    //  init a matrix
    //  at each level L as i
    //    at each member of i row j
    //       if j = 0 add 1
    //       if j = i add 1
    //       else add triangle.get(i).get(j - 1) + triangle.get(i - 1).get(j)
    // Test:
    //   l = 3
    // #0
    //   j = 0 add 1
    // #1
    //   j = 0 add 1
    //   j = 1 add 1
    // #2
    //   j = 0 add 1
    //   j = 1 triangle(j,j-1) + triangle(j-1,j) === triangle(1,0) + triangle(0,1)
    // #3
    //   j = 1 triangle()
    //   j = 2 triangle(1,2) + triangle(2, 1)
    //
    // 19:44 minutes
    // Time: O(n^2)
    // Space: O(n^2)
    public List<Integer> solve_array(int l)  {
        List<List<Integer>> triangle = new ArrayList<>();
        for (int i = 0; i <= l; i++)  {
            triangle.add(new ArrayList<>());
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) triangle.get(i).add(1);
                else {
                    triangle.get(i).add(
                        triangle.get(i - 1).get(j) + triangle.get(i - 1).get(j - 1)
                    );
                }
            }
        }
        return triangle.get(l);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 7; i++) {
            System.out.println(
                new PascalsTriangle().solve_array(i)
            );
        }
    }

    // triangle(3, 3)
    // triangle(2, 3)         triangle(3, 2)
    // triangle(1, 3) triangle(2,2)
    // triangle(0, 3) triangle(1,2) triangle(2,1) triangle(1,2)
    // triangle(0,2) triangle(1,1) triangle(1,1) triangle(2,0) triangle(0,2) triangle(1,1)
    //


    // 1 1
    // 1 0  0 1

    // 2 2
    // 1 2            2 1
    // 0 2  1 1       2 0  1 1
    //      1 0  0 1       1 0  0 1

}
