package strings;

import java.util.Arrays;

public class TestPalindromic {

    // For the purpose of this problem, define a palindromic string to be a string which
    // when all the nonalphanumeric are removed it reads the same front to back ignoring
    // case. For example, "A man, a plan, a canal, Panama." and "Able was I, ere I saw
    // Elba!" are palindromic, but "Ray a Ray" is not.
    //
    // Implement a function which takes as input a string s and returns true if s is a palin¬
    // dromic string.


    // Solution:
    //  given I cannot call String.replace methods
    //
    //  keep and index for start and end
    //   if either of them is not alphanumeric increment/decrement it
    //   if both alphanumeric check if they are equal
    //   repeat while left <= right
    //
    //  two minutes lost
    // Test:
    //  Ray a Ray
    //  ^       ^ -> false
    //
    //  A man A,
    //  ^      ^
    //  ^     ^
    //   ^   ^
    //    ^  ^
    //    ^ ^ -> false
    //
    //  A mam A
    // Time: O(n)
    // Space: O(1)
    //
    // SolutionTime: 6:56


    public boolean solve(String s) {
        String lower = s.toLowerCase();
        int left = 0;
        int right = lower.length() - 1;
        while (left <= right) {
            char l = lower.charAt(left);
            char r = lower.charAt(right);
            if (Character.isAlphabetic(l) && Character.isAlphabetic(r)) {
                if (r != l) return false;
                left++;
                right--;
            }
            if (!Character.isAlphabetic(l)) left++;
            if (!Character.isAlphabetic(r)) right--;
        }
        return true;
    }

    public static void main(String[] args) {
        TestPalindromic tp = new TestPalindromic();
        for (String s : Arrays.asList(
            "A mam A,",
            "A man A",
            "Ray a Ray",
            "A man, a plan, a canal, Panama.",
            "Able was I, ere I saw Elba!"
        )) {
            System.out.println(s + "   " + tp.solve(s));
        }
    }
}
