package search;

public class SearchEntryEqualToIndex {

    // Design an efficient algorithm that takes a sorted array of distinct integers, and returns
    // an index i such that the element at index i equals i. For example, when the input is
    // (-2,0, 2,3, 6,7,9) your algorithm should return 2 or 3.
    //
    // Example:
    //  -10 -5 0 3 6
    // Solution:
    //   we can easily skip all entries to the left of arr[i] wher arr[i] < i
    //   same with
    //                                                             arr[i] > i
    // so we are effectively looking for arr[i] - i == 0
    //                                   if arr[i] - i < 0  search in the right part
    //                                   if arr[i] - i > 0  search in the left part
    //
    // Solution:
    // binary search with custom condition
    // Test:
    //  -10 -5 0 3 6
    //         ^      0 - 2 == -2 < 0 | search right
    //           ^    3 - 3 == 0      | solution

    public static int find(int[] arr) {
        int l = 0, h = arr.length - 1;
        while (l <= h) {
            int mid = (l + h) >>> 1;

            int cmp = arr[mid] - mid;

            if (cmp < 0) l = mid + 1;
            else if (cmp > 0) l = mid - 1;
            else return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(find(new int[]{-10, -5, 0, 3, 6}));
        System.out.println(find(new int[]{-2, 0, 2, 3, 6, 7, 9}));
    }
}
