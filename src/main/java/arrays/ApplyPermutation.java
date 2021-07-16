package arrays;

import java.util.Arrays;

// solution
//             0
//   given: A: a b c d
//          P: 3 0 1 2
//   result:   b c d a
//   test
//             > i=0, write A[0] to R[3]
//             > i=1, write A[1] to R[0]
//             ...
//
//   init new array
//   for i in len(P):
//     R[P[i]] = A[i]

public class ApplyPermutation {
    public char[] apply(char[] set, int[] p) {
        char[] result = new char[set.length];
        for (int i = 0; i < set.length; i++) {
            result[i] = set[p[i]];
        }
        return result;
    }

    public static void main(String[] args) {
        ApplyPermutation ap = new ApplyPermutation();
        System.out.println(
            Arrays.toString(ap.apply(new char[]{'a', 'b', 'c', 'd'}, new int[]{3, 2, 1, 0}))
        );
    }
}
