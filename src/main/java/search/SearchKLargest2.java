package search;

import java.util.Arrays;
import java.util.Random;

public class SearchKLargest2 {
    // Many algorithms require as a subroutine the computation of the kth largest element
    // of an array. The first largest element is simply the largest element. The nth largest
    // element is the smallest element, where n is the length of the array.
    //
    // For example, if the input array A = (3, 2,1,5,4), then A[3] isthe first largest element
    // in A,A[0] is the third largest element in A, and A[ 2] is the fifth largest element in A.
    //
    // Design an algorithm for computing the kth largest element in an array. Assume
    // entries are distinct.

    // Solutions:
    // - sort and pick len - k - 1
    //   Time: O(n log n)
    // - use min heap to track k elements
    //   Time: O(n log k)
    // - custom algo
    //   divide&conquer
    //   choose pivot
    //   rearrange elements around it
    //   if right side is less than k
    //      then pick left
    //      else pick right
    //   as soon as right side of len 1 return

    // Test:
    //  10, 4, 7, 1, 11, 12    k = 2
    // p=7, arr[10 4 12 1 11 7]
    //         [10 4 12 1 11 7] 10 > 7
    //         [4 10 12 1 11 7] 4 < 7
    //         [4 10 12 1 11 7] 12 > 7
    //         [4 1 12 10 11 7] 1 < 7
    //         ...
    //         [4 1 7 10 11 12] 1 < 7
    //          0 1 2 3  4  5
    // 5 - 2 == 3 > k, pick right, pivot 11]
    // [7 10 12 11]
    // [7 11 12 10]
    //  2 3  4  5
    //  5 - 3 > 1
    // [11 10 12] p=12
    // 5 - 5 < 1
    // [10 11] pivot=11
    //  4 -4 < 1
    //  10
    //

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static int partitionBy(int pivotIdx, int low, int high, int[] arr) {
        int pivotVal = arr[pivotIdx];
        swap(arr, pivotIdx, high);
        int newPivotIdx = low;
        for (int i = low; i < high; i++) {
            if (arr[i] < pivotVal) {
                swap(arr, i, newPivotIdx++);
            }
        }
        swap(arr, high, newPivotIdx);
        return newPivotIdx;
    }

    public static Integer find(int[] arr, int k) {
        if (k > arr.length) return null;
        int target = arr.length - k;

        Random rand = new Random();
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int pivotIdx = low + rand.nextInt(high - low + 1);
            int newPivotIdx = partitionBy(pivotIdx, low, high, arr);
            if (newPivotIdx == target) return arr[newPivotIdx];
            else if (newPivotIdx > target) high = newPivotIdx - 1;
            else /* high - newPivotIdx < target */ low = newPivotIdx + 1;
        }

        return null;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 6; i++) {
            // 1 4 7 10 11 12
            System.out.println(find(new int[]{10, 4, 7, 1, 11, 12}, i));
            System.out.println();
        }
    }

}
