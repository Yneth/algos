package strings;

import java.util.Arrays;

public class FindFirstSubstring {

    // A good string search algorithm isfundamental to the performance of many applica¬
    // tions. Several clever algorithms have been proposed for string search, each with its
    // own trade-offs. As a result, there is no single perfect answer. If someone asks you this
    // question in an interview, the best way to approach this problem would be to work
    // through one good algorithm in detail and discuss at a high level other algorithms.
    // Given two strings (the "search string") and t (the "text"), find the first occurrence of s in t.

    // Solution:
    // Brute-force
    // if len(searchString) > len(str) return false || -1
    // search for the first occurrence ss[0] == str[x] and check all the following characters
    //  if no match continue to the end of the string
    //  else return true || i
    // Test:
    //  aabc   c
    //  ^
    //   ^
    //    ^
    //     ^ match  i=3 j=0  i+j = 3   j++  j=1 return

    public int search(String str, String search) {
        if (search.length() > str.length()) return -1;

        for (int i = 0; i < str.length(); i++) {

            int j = 0;
            while ((i + j) < str.length()
                && j < search.length()
                && search.charAt(j) == str.charAt(i + j)) {
                j++;
            }
            if (j == search.length()) {
                return i;
            }
        }
        return -1;
    }

    // rabin-karp
    // the idea is to use hashCodes (fingerprints)
    // first we calculate the hashcodes of search string
    // and len(search) of base string
    // m
    // then
    // we go from s.length to len(str)
    //   if hashSearch == hashString && search.equal(str.subs(i - len(s), i))
    //     return i
    //   otherwise
    //   subtract hashCode of first character
    //   add hashCode of the last character
    // n
    // the whole solution is based on a good hashcode function
    // Time:  O(m + n)
    // Space: O(1)
    // hash function is based on polynomial equation
    //   lets say we have s = abc   and base = 3(means we have total of 3 chars in alphabet)
    //   with hash values     012
    //    then its hash would be 3^2 * 0 + 3^1 * 1 + 2 = 0 + 3 + 2
    //
    //  polynomial equation for the string hashCode
    //   SUM i..|s| (a[i] ^ (base - i))
    //
    // to calculate the next one we would need to
    //  subtract the first one
    //   which would always have the base of base ^ |s|
    //  hashCode(i + 1) = hashCode(i) - char(i - |s|) * base^|s|
    //
    // overflow will do nothing as still the values calculated in the same manner
    //
    // overflow for base=32 will happen at |s| > 6
    //  (2^5)^x = 2^31
    //  2^5x = 2^31
    //  5x = 31
    // x ~= 6
    public int rabinKarp(String t, String s) {
        if (t.length() < s.length()) return -1;

        final int base = 26;
        int power = 1;
        int hashT = 0;
        int hashS = 0;
        for (int i = 0; i < s.length(); i++) {
            power = i == 0 ? 1 : power * base;
            hashT = hashT * base + t.charAt(i);
            hashS = hashS * base + s.charAt(i);
        }

        for (int i = s.length(); i < t.length(); i++){
            if (hashT == hashS && s.equals(t.substring(i - s.length(), i))) {
                return i - s.length();
            }
            hashT -= t.charAt(i - s.length()) * power;
            hashT = hashT * base + t.charAt(i);
        }
        if (hashT == hashS && s.equals(t.substring(t.length() - s.length()))) {
            return t.length() - s.length();
        }
        return -1;
    }

    public static void main(String[] args) {
        FindFirstSubstring ffs = new FindFirstSubstring();
        System.out.println(ffs.rabinKarp("aabc", "c"));
        System.out.println(ffs.rabinKarp("aabc", "bc"));
        System.out.println(ffs.rabinKarp("aabc", "abc"));
        System.out.println(ffs.rabinKarp("aabc", "aabc"));
        System.out.println(ffs.rabinKarp("aabc", "g"));
        System.out.println(ffs.rabinKarp("aabc", "ab"));
        System.out.println(ffs.rabinKarp("aabc", "bcc"));
        System.out.println(ffs.rabinKarp("aabc", "bccccc"));
        System.out.println(ffs.rabinKarp("aaaaabbbbbbbbbbbbbb", "aaaaabbbbbbbbbbbbbb"));
    }

}
