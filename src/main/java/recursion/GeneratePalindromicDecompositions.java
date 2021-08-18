package recursion;

import java.util.*;

public class GeneratePalindromicDecompositions {
    //  A string is said to be palindromic if it reads the same backwards and forwards. A
    // decomposition of a string is a set of strings whose concatenation is the string. For
    // example, "611116" is palindromic, and "611", "11", "6" is one decomposition for it.
    //  Compute all palindromic decompositions of a given string. For example, if the string
    // is "0204451881", then the decomposition "020", "44", "5", "1881" is palindromic, as
    // is "020", "44", "5", "1", "88", "1". However, "02044, "5", "1881" is not a palindromic
    // decomposition
    //
    // Example:
    //
    // Solution:
    //  gen(str, offset, partial, result)
    //    if offset == len(str)
    //       return result.add(partial)
    //    for (i in offset+1..len(str))
    //       if (palindrome(str.subs(offset, i))
    //          gen(str, i, partial ++ [subs], result)

    private static List<List<String>> gen(String s, int offset, List<String> partial, List<List<String>> res) {
        if (offset == s.length()) {
            res.add(new ArrayList<>(partial));
            return res;
        }
        for (int i = offset + 1; i <= s.length(); i++) {
            String sub = s.substring(offset, i);
            if (isPalindrome(sub)) {
                partial.add(sub);
                gen(s, i, partial, res);
                partial.remove(partial.size() - 1);
            }
        }
        return res;
    }

    public static boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1))
                return false;
        }
        return true;
    }

    public static List<List<String>> gen(String s) {
        return gen(s, 0, new ArrayList<>(), new ArrayList<>());
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("112211"));
        System.out.println(gen("0204451881"));
    }
}
