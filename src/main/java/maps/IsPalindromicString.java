package maps;

import java.util.HashMap;
import java.util.Map;

public class IsPalindromicString {

    // A palindrome is a string that reads the same forwards and backwards, e.g., "level",
    // "rotator", and "foobaraboof".
    // Write a program to test whether the letters forming a string can be permuted to form
    // a palindrome. For example, "edified" can be permuted to form "deified".

    // Solution:
    // - collect all characters to a map
    //   iff each character is seen event times then we have a palindromic permutation
    //    + if string is of odd len, it should have exactly one character with odd count
    //
    // pseudocode:
    //   form a map of char occurrences
    //   odd_encounters = len(str) % 2 == 1 ? 0 : 1;
    //   for k,v in occurrences:
    //     if v % 2 == 1: odd_encounters--
    //     if odd_encounters < 0: return false
    //   return true
    // Test:
    //  foobaraboof
    //  f 2    1
    //  o 4    1
    //  b 2    1
    //  a 2    1
    //  r 1    0
    // True
    //  abbbca
    //  a 2
    //  b 3
    //  c 1
    //

    public static boolean test(String str) {
        Map<Character, Integer> occurrences = new HashMap<>();
        for (char ch : str.toCharArray()) {
            occurrences.put(ch, occurrences.getOrDefault(ch, 0) + 1);
        }
        int oddCount = str.length() % 2 == 0 ? 0 : 1;
        for (Map.Entry<Character, Integer> e : occurrences.entrySet()) {
            if (e.getValue() % 2 == 1) oddCount--;
            if (oddCount < 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(test("rotator"));
        System.out.println(test("foobaraboof"));
        System.out.println(test("edified"));
        System.out.println(test("level"));
        System.out.println(test("abbbaa"));
    }
}
