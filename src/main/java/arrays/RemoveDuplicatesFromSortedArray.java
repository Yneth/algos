package arrays;

import java.util.Arrays;

public class RemoveDuplicatesFromSortedArray {
    // simple solution:
    // iterate over the array, when hit duplicates, count them
    // and then move all elements to the left when duplicates occur
    //
    // test:
    // 1 2 3 3 3 5 7
    // ^
    //   ^
    //     ^ 1
    //       ^ 2
    //         ^ 3
    //           ^
    // 1 2 3 5 7
    //

    public int[] removeDuplicates_wrong(int[] arr) {
        int n = arr.length;
        int dups = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i] == arr[i - 1]) {
                dups++;
            } else if (arr[i] != arr[i - 1] && dups > 0) {
                for (int j = dups; j > 0; j--) arr[i - j] = 0;

                for (int j = i; j < n; j++) {
                    int tmp = arr[j];
                    arr[j] = arr[j - dups];
                    arr[j - dups ] = tmp;
                }
                n -= dups - 1;
            }
        }
        return arr;
    }

    public int[] removeDuplicates(int[] arr)  {
        int writeIndex = 1;
        int maxElement = Integer.MIN_VALUE;
        for (int i = 1; i < arr.length; i++) {
            maxElement = Math.max(maxElement, arr[i]);
            if (arr[i] != arr[i - 1]) {
                arr[writeIndex++] = arr[i];
            }
        }
        for (int i = writeIndex; i < arr.length; i++) {
            arr[i] = 0;
        }
        return arr;
    }

    public static void main(String[] args) {
        RemoveDuplicatesFromSortedArray rdfsa =
            new RemoveDuplicatesFromSortedArray();

        System.out.println(
            Arrays.toString(
                rdfsa.removeDuplicates(new int[]{1, 2, 3, 3, 3, 5, 7})
            )
        );
        System.out.println(
            Arrays.toString(
                rdfsa.removeDuplicates(new int[]{1, 2, 3, 3, 3, 7, 7})
            )
        );
    }
}
