package strings;

import java.util.Arrays;

public class RunLengthEncoding {
    // Run-length encoding (RLE) compression offers a fast way to do efficient on-the-fly
    // compression and decompression of strings. The idea is simple—encode successive
    // repeated characters by the repetition count and the character. For example, the RLE
    // of "aaaabcccaa" is "4alb3c2a". The decoding of "3e4f2e" returns "eeeffffee".
    // Implement run-length encoding and decoding functions. Assume the string to be
    // encoded consists of letters of the alphabet, with no digits, and the string to be decoded
    // is a valid encoding.


    // Solution:
    // Encoding - counting problem
    // just iterate counting a character
    // as soon as it gets changed or we hit the end of the string
    // drop
    //
    // Decoding
    // read digit (make sure to handle numbers > 10)

    public String encode(String str) {
        String fixStr = str + " ";
        StringBuilder result = new StringBuilder();
        int count = 1;
        for (int i = 0; i < fixStr.length() - 1; i++) {
            if (fixStr.charAt(i) == fixStr.charAt(i + 1)) count++;
            else {
                if (count > 1) result.append(count).append(str.charAt(i));
                else result.append(str.charAt(i));
                count = 1;
            }
        }
        return result.toString();
    }

    public String decode(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            int count = Character.isDigit(str.charAt(i)) ? 0 : 1;
            while (Character.isDigit(str.charAt(i)))
                count = count * 10 + (str.charAt(i++) - '0');

            for (int j = 0; j < count; j++)
                result.append(str.charAt(i));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        RunLengthEncoding rle = new RunLengthEncoding();
        for (String s : Arrays.asList(
            "aaabbe",
            "aaaaaa",
            "aaaccc"
        )) {
            System.out.println(rle.encode(s));
            System.out.println(rle.decode(rle.encode(s)));
            System.out.println();
        }
    }

}
