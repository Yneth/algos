package strings;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReplaceAndRemove {
    //Consider the following two rules that are to be applied to an array of characters.
    // • Replace each 'a' by two 'd's.
    // • Delete each entry containing a 'b'.
    // For example, applying these rules to the array (a,c,d,b,b,c,a) results in the array
    // (d, d, c, d, c, d, d).
    // Write a program which takes as input an array of characters, and removes each 'b' and
    // replaces each 'a' by two 'd's. Specifically, along with the array, you are provided an
    // integer-valued size. Size denotes the number of entries of the array that the operation
    // is to be applied to. You do not have to worry preserving about subsequent entries. For
    // example, if the array is (a,b,a,c,_) and the size is 4, then you can return (d,d,d,d,c).
    // You can assume there is enough space in the array to hold the final result.

    // solution:
    // calculate resulting size of the modifications and non modified chars
    // for i in 0..n:
    //    total += (if a: 2 elif d: 0 else 1)
    //
    // r = 0
    // for i in 0..n
    //    if 'b' arr[r] none
    //    else arr[r] = arr[r++]
    //
    //
    // SolutionTime: 13:20
    // Did not solve


    // solution:
    // count a's and non b on the first iteration
    // iterate backwards using count(a) + count(non b) - 1
    // will do by having two indices
    // one for writing and one for reading
    //
    // test:
    // 0 1 2 3
    // a a b d
    // writerIndex = 2 + 2 + 1 - 2 = 4
    // readerIndex = n = 3
    //       d   w4 r3
    //     -     w3 r2
    //   a       w1 r0
    // a         w0 r0
    //
    // Time: O(2n) ~= O(n)
    // Space: O(1)
    //
    // SolutionTime: 21:12

    public int solve(char[] chs, int n) {
        int aCount = 0;
        int writeIdx = 0;
        for (int i = 0; i < n; i++) {
            char ch = chs[i];
            if (ch != 'b') writeIdx++;
            if (ch == 'a') aCount++;
        }

        writeIdx = writeIdx + aCount - 1;
        int size = writeIdx + 1;
        int readIdx = n - 1;
        while (writeIdx >= 0) {
            char ch = chs[readIdx--];
            if (ch == 'a') {
                chs[writeIdx--] = 'd';
                chs[writeIdx--] = 'd';
            } else if (ch != 'b') {
                chs[writeIdx--] = ch;
            }
        }
        return size;
    }

    public static void main(String[] args) {
        ReplaceAndRemove rar = new ReplaceAndRemove();
        List<char[]> tests = Arrays.stream(
            new String[]{
                "aabc_",
                "aa__d_",
                "aad_-+",
                "aabbd_"
            }
        ).map(String::toCharArray)
            .collect(Collectors.toList());

        for (char[] str : tests) {
            int n = rar.solve(str, 4);
            System.out.println(str);
            System.out.println(n);
        }
    }
}
