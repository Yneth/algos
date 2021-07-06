package arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// solution
//    multiply like it was done in school
//    123
//    333
//    ---
//    369
//   369
//  369
//  40959
// or alternative solutions
// multiply first int by each number of the second like
// 369, 3690, 36900
//
public class MultiplyPrecisionIntegers {
    public List<Integer> mul(int[] a, int[] b) {
        int sign = a[0] * b[0] > 0 ? 1 : -1;
        a[0] = Math.abs(a[0]);
        b[0] = Math.abs(b[0]);

        List<Integer> result = new ArrayList<>(
            Collections.nCopies(a.length + b.length, 0)
        );
        for (int i = a.length - 1; i >= 0; i--) {
            for (int j = b.length - 1; j >= 0; j--) {
                int val = a[i] * b[j] + result.get(i + j + 1);
                result.set(i + j + 1, val % 10);
                if (val >= 10) result.set(i + j, result.get(i + j) + (val / 10));
            }
        }
        while (result.size() > 1 && result.get(0) == 0)
            result.remove(0);
        if (sign < 0) result.set(0, result.get(0) * sign);
        return result;
    }

    public static void main(String[] args) {
        MultiplyPrecisionIntegers mpi = new MultiplyPrecisionIntegers();
        System.out.println(mpi.mul(new int[]{1, 2, 3}, new int[]{3, 3, 3}));
        System.out.println(mpi.mul(new int[]{9, 9, 9}, new int[]{9, 9, 9}));
        System.out.println(mpi.mul(new int[]{9, 9, 9}, new int[]{1}));
        System.out.println(mpi.mul(new int[]{1}, new int[]{9, 9, 9}));
        System.out.println(mpi.mul(new int[]{0}, new int[]{9, 9, 9}));
        System.out.println(mpi.mul(new int[]{9, 9, 9}, new int[]{0}));
        System.out.println(
            mpi.mul(
                new int[]{1, 9, 3, 7, 0, 7, 7, 2, 1},
                new int[]{-7, 6, 1, 8, 3, 8, 2, 5, 7, 2, 8, 7}
            )
        );
    }
}
