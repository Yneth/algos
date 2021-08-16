package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ThreeSum {
    // Given array of integers find all triples that sum up to X.
    //
    // Is given array sorted?
    // nope
    // Should result include duplicates?
    // yes
    //
    //
    // Solution:
    // Brute force:
    //   Time: O(n^3)
    //
    // Map solution:
    // simplify problem to two 2sum sub problems
    //
    // Brute force optimized:
    // for each index search triplets by maintaining other two indices
    //  start,end
    //  if i + j + k == sum
    //    j++; k--;
    //  else i + j + k < sum
    //    j++
    //  else i + j + k > sum
    //    k--

    public static List<List<Integer>> find(List<Integer> arr, int target) {
        Collections.sort(arr);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            int a = arr.get(i);

            int j = i + 1;
            int k = arr.size() - 1;
            while (j < k) {
                int b = arr.get(j);
                int c = arr.get(k);

                int sum = a + b + c;
                if (sum == target) {
                    result.add(Arrays.asList(i, j, k));
                    if (arr.get(j + 1) == b) {
                        j++;
                    } else if (arr.get(k - 1) == c) {
                        k--;
                    } else {
                        j++;
                        k--;
                    }
                } else if (sum < target) {
                    j++;
                } else if (sum > target) {
                    k--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(
            find(Arrays.asList(0, 0, 1, 2, 3, 4, 10, 10, 10, 20, 30), 30)
            //                 0  1  2  3  4  5  6   7   8   9   10
        );
    }

}
