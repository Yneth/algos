package search;

public class SearchCyclicallySortedArray {
    // An array issaid to be cyclically sorted if it is possible to cyclically shift its entries so
    // that it becomes sorted. For example, the array in Figure 12.2 on the facing page is
    // cyclically sorted—a cyclic left shift by 4 leads to a sorted array.
    //
    // Design an O(log n) algorithm for finding the position of the smallest element in a
    // cyclically sorted array. Assume all elements are distinct. For example, for the array
    // in Figure 12.2 on the next page, your algorithm should return 4.
    //
    // Example:
    //  8 10 1 2 3 4 6     k = 5  target: indexOf(1) == 2
    //
    // Solution:
    //   start at the middle
    //      compare l with mid
    //      compare h with mid
    //        if mid < mid - 1 solution
    //        if mid < l search left side
    //        if mid > h search right side
    // Test:
    //  8,10,1,2,3,4,5,6,7
    //           ^         3 < 8: go left
    //       ^             1 < 10 solution
    //
    //  3 1 2
    //    ^    1 < 3: solution
    //  6 7 1 2 3 4 5
    //        ^        2 < 6
    //    ^            7 > 5
    //      ^


    public static int find(int[] arr) {
        int l = 0, h = arr.length - 1;
        while (l <= h) {
            int mid = l + h >>> 1;
            if (arr[mid] < arr[mid - 1]) {
                return mid;
            } else if (arr[mid] < arr[l]) {
                h = mid - 1;
            } else if (arr[mid] > arr[h]) {
                l = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(find(new int[]{8, 10, 1, 2, 3, 4, 5, 6, 7}));// 2
        System.out.println(find(new int[]{11, 12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})); // 2
        System.out.println(find(new int[]{11, 12, 13, 14, 15, 16, 17, 18, 5, 6, 7, 8, 9, 10})); // 8
    }

}
