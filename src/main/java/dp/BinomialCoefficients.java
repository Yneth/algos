package dp;

import java.util.*;

public class BinomialCoefficients {
    //  The symbol (") is the short form for the expression ■ If is the number of
    // ways to choose a fc-element subset from an n-element set. It is not obvious that the
    // expression defining (") always yields an integer. Furthermore, direct computation of
    // (") from this expression quickly results in the numerator or denominator overflowing
    // if integer types are used, even if the final result fits in a 32-bit integer. If floats are
    // used, the expression may not yield a 32-bit integer.
    //  Design an efficient algorithm for computing (") which has the property that it never
    // overflows if the final result fits in the integer word size.

    // Solution:
    // - recall binomial recurrence equation
    //   |n|  =  |n - 1|  + |n - 1|
    //   |k|  =  |  k  |  + |k - 1|
    //
    // n == k :ret 1
    // k == 1 :ret n
    // n == k + 1 :ret n
    //
    // Test:
    //   4
    //   2
    //      3,2 + 3,1
    //      3 + 3 = 6
    //
    //
    // lets build recurrence graph
    //  5,2
    //     4,2        4,1
    //       3,2 3,1
    //
    // 5,3
    //    4,3         4,2
    //     3,3 3,2       3,2 3,1
    // 5,4
    //  4,4 4,3
    //    1   4
    //
    // how would dp table look like?
    //  lets create a list of pairs
    //   [[5,4]]
    //   [[4,4],[4,3]]
    //    1+4
    //
    // looks like a solution

    public static int coefficients(int n, int k) {
        if (k > n) throw new IllegalArgumentException("k cannot be larger than n");
        int result = 0;
        List<List<Integer>> dp = new ArrayList<>();
        dp.add(Arrays.asList(n, k));
        while (!dp.isEmpty()) {
            List<List<Integer>> next = new ArrayList<>();
            for (int i = 0; i < dp.size(); i++) {
                List<Integer> pair = dp.get(i);
                int nn = pair.get(0);
                int kk = pair.get(1);
                if (nn == kk + 1 || kk == 1) result += nn;
                else if (nn == kk || kk == 0) result += 1;
                else {
                    next.add(Arrays.asList(nn - 1, kk));
                    next.add(Arrays.asList(nn - 1, kk - 1));
                }
            }
            dp = next;
        }
        return result;
    }

    public static void main(String[] args) {
        for (int i = 1; i < 7; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.printf("(%s,%s) == %s\n",i,j, coefficients(i, j));
            }
        }
        System.out.println(coefficients(1000,321));
    }
}
