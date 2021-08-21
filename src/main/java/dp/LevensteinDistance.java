package dp;

public class LevensteinDistance {
    //  Spell checkers make suggestionsfor misspelled words. Given a misspelled string, a
    // spell checker should return wordsin the dictionary which are close to the misspelled
    // string.
    //  In 1965, Vladimir Levenshtein defined the distance between two words as the
    // minimum number of "edits" it would take to transform the misspelled word into a
    // correct word, where a single edit is the insertion, deletion, or substitution of a single
    // character. For example, the Levenshtein distance between "Saturday"and "Sundays"
    // is 4—delete the first 'a' and 't',substitute V by 'n' and insert the trailing 's'.
    //  Write a program that takes two strings and computes the minimum number of edits
    // needed to transform the firststring into the second string.
    // Assumptions:
    //
    // Solution:
    //   At each step we have a choice
    //      delete,update,insert
    //      when
    //         t[i] != c[j] 1 + min(dp(i - 1, j - 2), dp(i - 2, j - 1), dp(i - 2, j - 2))
    //         t[i] == c[j] i++ j++
    //
    //  saturday staurday
    //  ^        ^
    //   ^        ^        update
    //    ^        ^       update
    //     ^        ^
    //      ^        ^
    //       ^        ^
    //        ^        ^
    //         ^        ^
    // dp(i,j) = | a[i] == b[j] = dp(i - 1, j - 1)
    //           | 1 + min(...)
    //
    //  now lets' analyze table
    //  it would be of size |a|x|b|
    //  a=abc b=acb
    //
    //
    //    0 1 2 3
    //  0 0 0 0 0
    //  1 0 0 0 0
    //  2 0 0 1 2
    //  3 0 0 2 1
    // recursive solution:

    public static int rec(String a, String b, int i, int j) {
        if (i < 0) return j + 1;
        if (j < 0) return i + 1;
        if (a.charAt(i) == b.charAt(j))
            return rec(a, b, i - 1, j - 1);
        else {
            int add = rec(a, b, i - 1, j);
            int delete = rec(a, b, i, j - 1);
            int sub = rec(a, b, i - 1, j - 1);
            return 1 + Math.min(sub, Math.min(add, delete));
        }
    }

    public static int rec(String a, String b) {
        return rec(a, b, a.length() - 1, b.length() - 1);
    }

    public static int iter(String a, String b) {
        if (a.equals(b)) return 0;

        int aLen = a.length();
        int bLen = b.length();
        if (aLen == 0) return bLen;
        if (bLen == 0) return aLen;
        if (aLen < bLen) {
            int tmp = aLen;
            aLen = bLen;
            bLen = tmp;
            String tmpS = a;
            a = b;
            b = tmpS;
        }

        int[][] dp = new int[aLen + 1][bLen + 1];
        for (int i = 0; i <= aLen; i++) {
            for (int j = 0; j <= bLen; j++) {
                if (i == 0 || j == 0) dp[i][j] = i;
                else {
                    int cost = a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1;
                    dp[i][j] = Math.min(
                        dp[i - 1][j - 1] + cost,
                        Math.min(
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1
                        )
                    );
                }
            }
        }
        return dp[aLen][bLen];
    }

    public static void main(String[] args) {
        System.out.println(iter("saturday", "yadrutas"));
        System.out.println(rec("saturday", "yadrutas"));

        System.out.println(iter("cataabbccdd", "cat"));
        System.out.println(iter("cat", "cataabbccdd"));
        System.out.println(rec("cat", "cataabbccdd"));
        System.out.println(rec("cataabbccdd", "cat"));
    }
}
