package arrays;

import java.util.Arrays;

public class Rotate2DArray {
    // Image rotation isa fundamental operation in computer graphics. Figure 6.4 illustrates
    // the rotation operation on a 2D array representing a bit-map of an image. Specifically,
    // the image is rotated by 90 degrees clockwise
    //
    // Write a function that takes as input an n X n 2D array, and rotates the array by
    // 90 degrees clockwise.
    //
    // Example:
    // 1 2
    // 3 4
    //
    // 3 1
    // 4 2


    // Solution:
    // iterate n / 2 times as i
    //  for j 0..i
    //    (0,i) (i,n) (n,n-i) (n-i,0)
    // Test:
    // 1 2 3
    // 4 5 6
    // 7 8 9
    //
    // #0:
    // (0,0) (0,2) (2, 2) (2,0)
    // (0,1) (1,2) (2, 1) (1,0)
    //
    // 1  2  3  4
    // 5  6  7  8
    // 9  10 11 12
    // 13 14 15 16
    //
    // #0
    // 0,0   0,3   3,3   3,0
    // 0,1   1,3   3,2   2,0
    //
    // #1
    // 1,1   1,2   2,2   2,1

    public int[][] solve(int[][] arr) {
        int n = arr.length - 1;
        for (int i = 0; i < arr.length / 2; i++) {
            for (int j = i; j < n - i; j++) {
                int temp0 = arr[i][j];
                int temp1 = arr[j][n - i];
                int temp2 = arr[n - i][n - j];
                int temp3 = arr[n - j][i];
                arr[i][j] = temp3;
                arr[j][n] = temp0;
                arr[n][n - j] = temp1;
                arr[n - j][i] = temp2;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        Rotate2DArray r2da = new Rotate2DArray();

        Arrays.stream(
            r2da.solve(
                new int[][] {
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 16}
                }
            )
        ).forEach(x -> System.out.println(Arrays.toString(x)));
    }

    // Failed
    // 36 minutes

}
