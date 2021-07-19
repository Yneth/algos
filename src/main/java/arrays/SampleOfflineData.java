package arrays;

import java.util.Arrays;

public class SampleOfflineData {
    // Implement an algorithm that takes as input an array of distinct elements and a size,
    // and returns a subset of the given size of the array elements. All subsets should be
    // equally likely. Return the result in input array itself.

    // given arr & n
    // return arr[i..j]  j - i == n
    //
    // thoughts
    //  I think we can generate any random number R between 0 and arr.length - n
    //  and just take return arr[R..R + n]
    // sound like a solution
    //
    // Test:
    // 1 2 3        n = 2
    //              3 - 2 = 1
    //              random between [0,1]



    public int[] solve(int[] arr, int size) {
        int r = new java.util.Random().nextInt(arr.length - size + 1);

        for (int i = 0; i < arr.length; i++) {
            if (i >= size) arr[i] = 0;
            else {
                arr[i] = arr[r++];
            }
        }
        return arr;
    }

    // Completely wrong
    // The idea is to return random values
    // Solution:
    // iterate over 0..size
    // swap(i, i + rand(arr.len - i))

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public int[] solveCorrect(int[] arr, int size) {
        java.util.Random rand = new java.util.Random();
        for (int i = 0; i < size; i++) {
            swap(arr, i, i + rand.nextInt(arr.length - i));
        }
        return arr;
    }

    public static void main(String[] args) {
        SampleOfflineData sod = new SampleOfflineData();
        for (int i = 0; i < 10; i++)   {
            System.out.println(Arrays.toString(sod.solveCorrect(new int[] {1, 2, 3}, 2)));
        }
    }
}
