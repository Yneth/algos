package sorting;

import java.util.*;

public class MergeTwoSortedArrays {
    // MERGE TWO SORTED ARRAYS
    //  Suppose you are given two sorted arrays of integers. If one array has enough empty
    // entries at its end, it can be used to store the combined entries of the two arrays in
    // sorted order. For example, consider (5,13,17, J) and (3,7,11,19), where ~
    // denotes an empty entry. Then the combined sorted entries can be stored in the first
    // array as(3,5,7,11,13,17,19, „).
    //  Write a program which takes asinput two sorted arrays of integers, and updates the
    // first to the combined entries of the two arraysin sorted order. Assume the first array
    // has enough empty entries at its end to hold the result.
    //
    // Example:
    // 5 13 17
    // 3 7 11 19
    //
    // Solutions:
    // - straight forward solution would be to
    //   pick the smalest of the two
    //   if it is in the left
    //    just iterate left
    //      otherwise move all elements forward and insert
    //
    // - what if we swap the higher element back to the right array
    //
    //   3 13 17
    //   5 7 11 19
    //
    //   3 5 17
    //   13 7 11 19
    //
    //   3 5 7
    //   13 17 11 19
    //
    // - what if we use min heap for such elements
    //   3 13 17       heap[5]
    //   3 7 11 19
    //
    //   3 5 17        heap[13,7]
    //   x x 11 19
    //
    //   3 5 7      heap[17,11]
    //   x x x 19
    //
    //   3 5 7 11   heap[19,17]
    //   x x x x
    // Seems like a solution
    //
    // Time: O(n log m)
    // Space: O(m)
    //
    // - Wow, actual and really easy solution would be to start writing from the end
    //
    //

    public static int[] merge(int[] a, int len, int[] b) {
        int writeIdx = a.length - 1;
        int leftIdx = len - 1, rightIdx = b.length - 1;
        while (leftIdx >= 0 && rightIdx >= 0) {
            int cmp = Integer.compare(a[leftIdx], b[rightIdx]);
            if (cmp > 0) {
                a[writeIdx] = a[leftIdx--];
            } else if (cmp < 0) {
                a[writeIdx] = b[rightIdx--];
            } else {
                a[writeIdx] = a[rightIdx--];
            }
            writeIdx--;
        }
        while (leftIdx >= 0) a[writeIdx--] = a[leftIdx--];
        while (rightIdx >= 0) a[writeIdx--] = b[rightIdx--];
        return a;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(merge(
            new int[] {1, 2, 3, 3, 0, 0, 0, 0}, 4,
            new int[] {-100,-10, 0, 0}
        )));
    }

}
