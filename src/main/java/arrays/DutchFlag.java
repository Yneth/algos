package arrays;

import java.util.Arrays;

public class DutchFlag {
    // Solution:
    // for each value if A except P
    // if A[i] < A[P]: swap(A, i, p++)
    // if A[i] > A[P]: swap(A, i, p + 1)
    // wrong
    //
    // put A[p] at the start of the array
    // then for each value
    // if A[i] < A[p]: swap(A, i, p - 1)
    // if A[i] > A[p]: swap(A, i, p + 1)
    //
    // Test:
    //  1 2 3 0 4 6     p = 3, A[p] = 0
    //  -> 0 1 2 3 4 6
    //  -> 0 1 2 3 4 5
    //
    //  1 2 3 0 4 6   p = 2, A[p] = 3
    //  -> 3 2 1 0 4 6   put 3 at the start
    //  -> 2 3 1 0 4 6
    //  -> 2 1 3 0 4 6
    //  -> 2 1 0 3 4 6
    //
    //  100 2 4 7 1 11       4
    //  -> 4 2 100 7 1 11
    //  -> 2 4 100 7 1 11     swap 2 and 4
    //  -> 2 4 100 7 1 11     nothing
    //  -> 2 4 100 7 1 11     nothing
    //  -> 2 4 100 7 1 11
    //  -> -> 2 4 100 1 7 11
    //  -> -> 2 4 1 100 7 11
    //  -> -> 2 1 4 100 7 11

    public static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    // given an array A and index - p
    // move all elements lt A[p] to left
    //                   gt A[p] to right
    //    ^^^
    //   insertion sort
    //
    // but we can do better
    // by ruling three indices
    // for lower, equal and greater
    public void solve(int[] xs, int p) {
        int pivot = xs[p];
        int l = 0, m = 0, h = xs.length - 1;
        while (m <= h) {
            if (xs[m] == pivot) {
                m++;
            } else if (xs[m] > pivot) {
                swap(xs, m, h--);
            } else if (xs[m] < pivot) {
                swap(xs, l++, m++);
            }
        }
    }

    private static void run(int[] data, int p) {
        new DutchFlag().solve(data, p);
        System.out.println(Arrays.toString(data));
    }

    public static void main(String[] args) {
        run(new int[]{100, 2, 1, 7, 7, 14}, 0);
        run(new int[]{100, 2, 1, 7, 7, 14}, 2);
        run(new int[]{100, 2, 1, 7, 7, 14}, 3);
        run(new int[]{1, 1, 0, 2}, 3);
    }

}
