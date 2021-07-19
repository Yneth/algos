package arrays;

import java.util.Arrays;

public class NextPermutation {
    // given permutation Pn
    // return permutation Pn+1 in dictionary ordering
    // dictionary ordering -> Pn > Pn+1 if any Pn[i] > Pn+1[i]
    //
    // questions:
    // does Pn always have values from 0 to N? assume, yes
    //
    // need to know max value
    //
    // tests:
    // 1 0 2   given max value of 2, we see that the next possible value for 0 would be 2
    //         how did I pick 0? it looks like we always swap the min value with next value to min (test2)
    // 1 2 0
    // 2 0 1
    // 2 1 0
    //
    // #2
    // 1 2 0 3
    // 1 2 3 0
    //
    //
    // #3
    // 0 1 2 3 the first value defines the level we are currently on
    //         to find next permutation for current level
    //         we need to swap first two ordered elements
    //              2 and 1
    // 0 2 1 3      1 and 3
    // 0 2 3 1      2 and 3
    // 0 3 1 2      1 and 2
    // 0 3 2 1      when all elements are out of order we need to fix next P[0]+1 at P[0] with all next elements in order
    // 1 0 2 3
    // 1 2 0 3
    // 1 2 3 0
    // 2 0 1 3
    // 2 1 0 3
    // 2 1 3 0
    // 3 0 1 2
    // 3 1 0 2
    // 3 1 2 0
    // 3 2 1 0
    // solution:
    //
    // find first pair of elements that are out of order and swap them
    // if there are no such elements, set P[0] = P[0] + 1

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public int[] solve(int[] arr) {
        boolean hasOrdered = false;
        int i = 1;
        for (; i < arr.length - 1; i++) {
            if (arr[i] < arr[i + 1]) {
                hasOrdered = true;
                break;
            }
        }

        if (!hasOrdered && arr[0] == arr.length - 1 && arr[arr.length - 1] == 0)
            return arr;

        if (hasOrdered) {
            swap(arr, i, i + 1);
        } else {
            int v = 0;
            arr[0] = Math.min(arr[0] + 1, arr.length - 1);
            for (int j = 1; j < arr.length; j++) {
                if (v == arr[0]) v++;
                arr[j] = v++;
            }
        }
        return arr;
    }

    // took 37 minutes to solve
    // actual solution is wrong
    // we need to swap not just i & i + 1
    // but i with    for x in P:  min(P[x] > P[i])
    public static void main(String[] args) {
        NextPermutation np = new NextPermutation();
        int[] arr = new int[]{1, 0, 3, 2};
        for (int i = 0; i < 23; i++) {
            System.out.println(Arrays.toString(np.solve(arr)));
        }

        System.out.println(Arrays.toString(np.solve(new int[] {1, 0, 3, 2})));
    }
}
