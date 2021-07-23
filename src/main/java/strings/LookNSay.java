package strings;

import java.util.Arrays;

public class LookNSay {
    // The look-and-say sequence starts with 1. Subsequent numbers are derived by de¬
    // scribing the previous number in terms of consecutive digits. Specifically, to generate
    // an entry of the sequence from the previous entry, read off the digits of the previ¬
    // ous entry, counting the number of digits in groups of the same digit. For exam¬
    // ple, 1; one 1; two Is; one 2 then one 1; one 1, then one 2, then two Is; three Is,
    // then two 2s, then one 1. The first eight numbers in the look-and-say sequence are
    //     <1,11, 21,1211,111221,312211,13112221,1113213211>.
    // Write a program that takes as input an integer n and returns the nth integer in the
    // look-and-say sequence. Return the result as a string.

    // Solution
    // Looks like a counting problem
    // we always start from 1
    // To calculate Nth we need to run N times
    //

    public String solve(int x) {
        String num = "1";
        for (int i = 0; i < x; i++) {
            num = genNextNum(num);
        }
        return num;
    }

    private String genNextNum(String n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n.length(); i++) {
            int count = 1;
            while (i + 1 < n.length() && n.charAt(i) == n.charAt(i + 1)) {
                count++;
                i++;
            }
            sb.append(count).append(n.charAt(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LookNSay lns = new LookNSay();
        for (int i = 0; i < 20; i++) {
            System.out.println(lns.solve(i));
        }
    }

}
