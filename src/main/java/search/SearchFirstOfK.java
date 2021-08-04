package search;

public class SearchFirstOfK {
    // Binary search commonly asks for the index of any element of a sorted array that is
    // equal to a specified element. The following problem has a slight twist on this
    //
    // Write a method that takes a sorted array and a key and returns the index of the
    // first occurrence of that key in the array. For example, when applied to the array in
    // Figure 12.1 your algorithm should return 3 if the given key is 108; if it is 285, your
    // algorithm should return 6.
    //

    // Examples:
    //    1,5,8,8,8,9,10,16,20
    //   k =8
    //
    //            ^  match
    //        ^      match
    //      ^        return prev match
    // Solution:
    //  classic binary search with update
    //    when find matching value, continue dividing


    public static int find(int[] arr, int k) {
        int l = 0, h = arr.length - 1;
        int result = -1;
        while (l <= h) {
            int mid = (l + h) >>> 1;
            if (arr[mid] > k) {
                h = mid - 1;
            } else if (arr[mid] == k) {
                result = mid;
                h = mid - 1;
            } else if (arr[mid] < k) {
                l = mid + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(
            find(new int[]{1, 5, 8, 8, 8, 9, 10, 16, 20}, 8)
        );
        System.out.println(3 >>> 1);
    }

}
