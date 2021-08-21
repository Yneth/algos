package dp;

public class StairClimbing {
    //  You are climbing stairs. You can advance 1 to k steps at a time. Your
    // destination is exactly n steps up. Write a program which takes asinputs n and k and
    // returns the number of waysin which you can get to your destination. For example,
    // if n = 4 and k = 2, there are five waysin which to get to the destination:
    //  • foursingle stair advances,
    //  • two single stair advancesfollowed by a double stair advance,
    //  • a single stair advance followed by a double stair advance followed by a single
    //    stair advance,
    //  • a double stair advance followed by two single stairs advances, and
    //  • two double stair advances.
    //
    // n=4 k=2
    //
    // 0
    //  1           2
    //  2    3      3   4
    //  3 4  4      4
    //  4
    //
    // recursive solution:
    //   stairs(n, k)
    //      if n == 0 return 1
    //      if n < 0 return 0
    //      result = 0;
    //      for i in 1..k
    //        result += stairs()
    //      return result
    //
    // looks like an extended version of fib sequence


    public static int stairs(int n, int k) {
        if (n < 0) return 0;
        if (n == 0) return 1;
        int result = 0;
        for (int i = 1; i <= k; i++) {
            result += stairs(n - i, k);
        }
        return result;
    }

    public static int stairsIter(int n, int k) {
        if (n < 2) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = Math.max(i - k, 0); j < i; j++) {
                dp[i] += dp[j];
            }
        }
        return dp[n];
    }


    public static void main(String[] args) {
        for (int i = 0; i <= 10; i++) {
            System.out.printf("(i,j) = (%s,%s) = %s\n", i, 2, stairs(i, 3));
            System.out.printf("(i,j) = (%s,%s) = %s\n", i, 2, stairsIter(i, 3));
        }
    }
}
