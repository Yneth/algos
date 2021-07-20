package strings;

import java.util.*;

public class Atoi {

    // INTERCONVERT STRINGS AND INTEGERS
    // given string convert it to integer without lib usage
    // Example:
    // "123" => 123
    // Solution:
    //   Read first symbol to check sign
    //   init result value of 0
    //   Read rest of the string
    //      multiply result by 10 and add current integer
    //   return sign * res
    // 2 minutes for explanation
    //
    // Time: O(n)
    // Space: O(1)

    public int str2int(String str) {
        int sign = str.startsWith("-") ? -1 : 1;
        int i = sign > 0 ? 0 : 1;
        int result = 0;
        while (i < str.length()) {
            result = result * 10 + (str.charAt(i) - '0');
            i++;
        }
        return sign * result;
    }

    public String int2str(int i) {
        StringBuilder result = new StringBuilder();
        boolean isNegative = i < 0;
        int v = i;
        do {
            result.append(Math.abs(v % 10));
            v /= 10;
        } while (v != 0);
        if (isNegative) result.append('-');
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        List<String> strs = Arrays.asList(
            "123", "-123", "0",
            Integer.toString(Integer.MAX_VALUE),
            Integer.toString(Integer.MIN_VALUE)
        );
        Atoi a = new Atoi();
        for (String s : strs) {
            System.out.println(a.str2int(s));
        }

        List<Integer> ints = Arrays.asList(
            123, -123, 0, Integer.MAX_VALUE, Integer.MIN_VALUE
        );
        for (Integer i : ints) {
            System.out.println(a.int2str(i));
        }
    }

    // SOlution time: 6 minutes

}
