package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    //  Design an algorithm that takes as input an array and a number,and determines if there
    // are three entries in the array (not necessarily distinct) which add up to the specified
    // number. For example, if the array is (11,2,5,7,3) then there are three entries in the
    // array which add up to 21 (3,7,11 and 5,5,11). (Note that we can use 5 twice, since
    // the problem statement said we can use the same entry more than once.) However, no
    // three entries add up to 22.
    //
    // Given:
    //    arr -> [int], 0 < |arr| < n
    //
    // Solution:
    //  - sort the array
    //  - for each item
    //       start with two indices j,k
    //       j=i, k=len(arr)-1
    //       here we assume the invariant
    //       if i+j+k < t
    //        then we need to increment j (as there no such elements between i,j that would make up to TARGET)
    //        same with j,k in case of > t
    //            [j,k] contains values less than [k, |arr|] as arr is sorted
    //

    public static List<List<Integer>> threeSum(int[] arr, int target) {
        Arrays.sort(arr);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int a = arr[i];
            int j = i, k = arr.length - 1;
            while (j <= k) {
                int b = arr[j], c = arr[k];
                int sum = a + b + c;
                System.out.printf("(%s,%s,%s)=(%s,%s,%s)=%s\n", i, j, k, a, b, c, sum);
                if (sum == target) {
                    result.add(Arrays.asList(i, j++, k--));
                } else if (sum < target) {
                    j++;
                } else if (sum > target) {
                    k--;
                }
            }
        }
        return result;
    }

    public static List<List<Integer>> threeSumDistinct(int[] arr, int target) {
        Arrays.sort(arr);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int a = arr[i];
            int j = i + 1, k = arr.length - 1;
            while (j < k) {
                int b = arr[j], c = arr[k];
                int sum = a + b + c;
                System.out.printf("(%s,%s,%s)=(%s,%s,%s)=%s\n", i, j, k, a, b, c, sum);
                if (sum == target) {
                    result.add(Arrays.asList(i, j++, k--));
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
        // 2 3 5 7 11
        System.out.println(
            threeSum(new int[]{11, 2, 5, 7, 3}, 21)
        );

        System.out.println(
            threeSumDistinct(new int[]{11, 2, 5, 7, 3}, 21)
        );
    }

}
