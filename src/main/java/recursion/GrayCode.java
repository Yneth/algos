package recursion;

import java.util.*;

public class GrayCode {
    //  An n-bit Gray code is a permutation of {0,1, 2,...,2" - 1) such that the binary rep¬
    // resentations of successive integers in the sequence differ in only one place. (This
    // is with wraparound, i.e., the last and first elements must also differ in only one
    // place.) For example, both <(000)2,(100)2,(101)2,(111)2,(110)2,(010)2,(011)2,(001)2> =
    // (0, 4,5, 7,6, 2,3, 1) and (0, 1,3, 2,6, 7,5, 4) are Gray codesfor n = 3.
    //  Write a program which takes n asinput and returns an n-bit Gray code

    // 0,1
    // 01,00,10,11

    // grayCode(3)
    //   grayCode(0, 3 - 1)
    //     grayCode(0 << 1 + 0, 2 - 1)
    //       grayCode(0 << 1, 0)         0
    //       grayCode(0 << 1 + 1, 0)     1
    //     grayCode(0 << 1 + 1, 2 - 1)
    //       grayCode(1 << 1 + 0, 0)     2
    //       grayCode(1 << 1 + 1, 0)     3
    //   grayCode(1, 3 - 1)
    //     grayCode(1 << 1 + 0, 1)
    //       grayCode(2 << 1 + 0, 0)     4
    //       grayCode(2 << 1 + 1, 0)     5
    //     grayCode(1 << 1 + 1, 1)
    //       grayCode(3 << 1 + 0, 0)     6
    //       grayCode(3 << 1 + 1, 0)     7
    //

    private static List<Integer> gen(int val, int n, List<Integer> result) {
        if (n == 0) {
            result.add(val);
            return result;
        }
        int bit = 1 << (n - 1);
        gen(val, n - 1, result);
        gen(val ^ bit, n - 1, result);
        return result;
    }

    public static List<Integer> gen(int n) {
        List<Integer> result = new ArrayList<>();
        gen(0, 3, result);
        return result;
    }

    public static List<String> genBinary(int n) {
        Queue<String> q = new LinkedList<>();
        q.add("0");
        q.add("1");
        while (--n > 0) {
            List<String> level = new ArrayList<>();
            while (!q.isEmpty()) {
                level.add(q.remove());
            }
            for (String s : level) {
                q.add(s + "0");
                q.add(s + "1");
            }
        }
        return new ArrayList<>(q);
    }

    public static void main(String[] args) {
        System.out.println(gen(3));
        System.out.println(genBinary(3));
    }

}
