package greedy;

import java.util.*;

public class NSum {
    //  Solve the same problem when k, the number of elements to sum, is an
    // additional input.
    //
    // Solution:
    //  need to write routine for two sum
    //  iterate over all values of array
    //    // recurse up until we have n == 2, and then use twoSum for the part of the array thats left
    //    nSum(arr, target - arr[i], i + 1, n - 1)
    //
    // Test:
    //   2 2 3 3
    //   nSum(10, 0, 4)
    //     nSum(8, 1, 3)
    //      nSum(6, 2, 2)
    //        nSum(3, 3, 1)
    //           return arr

    private static List<List<Integer>> nSum(int[] arr, int t, int i, int n, List<Integer> partial, List<List<Integer>> res) {
        System.out.println(partial);
        if (n > 2) {
            partial.add(i);
            nSum(arr, t - arr[i], i + 1, n - 1, partial, res);
            partial.remove(partial.size() - 1);
            return res;
        } else {
            int j = i, k = arr.length - 1;
            while (j < k) {
                int sum = arr[j] + arr[k];
                if (sum > t) {
                    k--;
                } else if (sum < t) {
                    j++;
                } else {
                    partial.add(j);
                    partial.add(k);
                    res.add(new ArrayList<>(partial));
                    partial.remove(partial.size() - 1);
                    partial.remove(partial.size() - 1);
                    j++; k--;
                }
            }
            return res;
        }
    }

    public static List<List<Integer>> nSum(int[] arr, int n, int target) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < arr.length - n; i++) {
            nSum(arr, target, i, n, new ArrayList<>(), result);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(
            nSum(new int[] {0, 1, 1, 2, 2, 3, 3, 5}, 4, 10)
            //              0  1  2  3  4  5  6  7
            //                 ^  ^        ^     ^
            //                    ^  ^  ^        ^
            //                       ^  ^  ^  ^
        );
    }

}
