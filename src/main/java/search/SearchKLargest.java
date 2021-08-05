package search;

import java.util.Arrays;
import java.util.Random;

public class SearchKLargest {
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
    // - sort and pick n - k - 1
    //   Time: O(n log n)
    //
    // - heap for k elements
    //   Time: O(n log k)
    //
    // - use pivot
    //   move all elements smaller than Pivot to the left
    //   move all elements higher             to the right
    //     then if len(smaller) > k proceed with left part
    //             len(higher) > k proceed with right part
    // Test:
    //  3,2,1,5,4        k=2
    //  pivot = 1
    //
    //  1,2,3,5,4 swap pivot with 3
    //  skip 2,3,5,4
    //
    // 2,3,5,4 pivot 3
    // 2,4,5,3;  2 < 3; newPivotIdx++
    // 2,4,5,3;  4 > 3;
    // 2,4,5,3;  5 > 3;
    // 2,3,5,4;  place3 at newPIvotIdx
    // proceed with right == 2
    // 5,4  pivot 4
    // 5,4;     5 > 4; newPivotIdx++;
    // 4,5; return 4
    //
    //
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static int partition(int l, int h, int pivot, int[] arr) {
        int pivotVal = arr[pivot];
        int newPivotId = l;
        swap(arr, pivot, h);
        for (int i = l; i < h; i++) {
            if (arr[i] < pivotVal)
                swap(arr, i, newPivotId++);
        }
        swap(arr, h, newPivotId);
        return newPivotId;
    }

    public static int find(int[] arr, int k) {
        Random r = new Random();
        int l = 0, h = arr.length - 1;
        while (l <= h) {
            int pivotIdx = l + r.nextInt(h + 1 - l);

            System.out.println(Arrays.toString(arr));
            int newPivotIdx = partition(l, h, pivotIdx, arr);

            System.out.println("pivot = " + arr[pivotIdx] + ", l=" + l + ", h=" + h + " " + Arrays.toString(arr));
            System.out.println(newPivotIdx);

            int cmp = h - newPivotIdx; // distance from h to pivot
            if (cmp > k) l = newPivotIdx + 1;
            else if (cmp < k) h = newPivotIdx - 1;
            else return newPivotIdx;
        }
        return l;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 1, 5, 4};
        System.out.println(find(arr, 2));
        System.out.println(Arrays.toString(arr));
    }
}
