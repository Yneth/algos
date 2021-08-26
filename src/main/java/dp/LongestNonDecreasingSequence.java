package dp;

import java.util.*;

public class LongestNonDecreasingSequence {
    //  The problem of finding the longest nondecreasing subsequence in a sequence of inte¬
    // gers has implications to many disciplines, including string matching and analyzing
    // card games. As a concrete instance, the length of a longest nondecreasing subse¬
    // quence for the array in Figure 17.15 is 4. There are multiple longest nondecreasing
    // subsequences, e.g., (0,4,10,14} and (0, 2,6,9). Note that elements of non-decreasing
    // subsequence are not required to immediately follow each other in the original se¬
    // quence.
    //  Write a program that takes asinput an array of numbers and returns the length of a
    // longest nondecreasing subsequence in the array.
    //
    // Example:
    //a 0 8 4 12 2 10 6 14 1 9
    //i 0 1 2  3 4  5 6 7  8 9
    //
    // Given: arr [0..n]
    //
    // lnds(i, c, arr)
    //   res = c
    //   for j i+1..len(arr)
    //     if arr[i] < arr[j]
    //       max(res, lnds(j, arr, c + 1))
    //
    //   return res
    //
    // lnds(0, 1)
    //   lnds(1, 2)
    //     lnds(3, 3)
    //        lnds(7, 4)
    //   lnds(2, 2)
    /// . ...
    //
    // we can cache results for i
    //
    // lest make iteratvie solution
    //
    //  we need to calculate max value for each entry
    //   for index i
    //       we can calculate decreasing
    //a 0 8 4 12 2 10 6 14 1 9
    //  1 2 2 3  2 3  3 4  2 4


    private static int find(int[] arr, int i, int count, int[] cache) {
        int result = count;
        for (int j = i + 1; j < arr.length; j++) {
            if (arr[i] < arr[j]) {
                result = Math.max(result, find(arr, j, count + 1, cache));
            }
        }
        cache[i] = result;
        return result;
    }


    public static int find(int[] arr) {
        return find(arr, 0, 1, new int[arr.length]);
    }

    public static int findIter(int[] arr) {
        int[] maxDecreasing = new int[arr.length];
        Arrays.fill(maxDecreasing, 1);
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] <= arr[i]) {
                    maxDecreasing[i] = Math.max(maxDecreasing[i], maxDecreasing[j] + 1);
                    max = Math.max(max, maxDecreasing[i]);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(findIter(new int[] {0, 8, 4, 12, 2, 10, 6, 14, 1, 9}));
    }
}
