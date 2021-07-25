package strings;

import java.util.Arrays;

public class SinusoidString {
    // WRITE A STRING SINUSOIDALLY
    // We illustrate what it means to write a string in sinusoidal fashion by means
    // of an example. The string "Hello.World!" written in sinusoidal fashion is
    //
    //    e        _       l
    //  H   l    o   W   r   d
    //         l       o       !
    //
    // Define the snakestring of s to be the left-right top-to-bottom sequence in which
    // characters appear when s is written in sinusoidal fashion. For example, the
    // snakestring string for "HelloÿWorld!" is "eÿlHloWrdlo!".
    // Write a program which takes as input a string s and returns the snakestring of s
    //
    //
    // Solution:
    // init 3xN char matrix
    // init positions array with size of 3 with values 0 1 -1
    // iterate over characters of S with index i
    //    matrix[positions[i % 3]][i] = word[i]
    //
    // Test:
    //   Hello
    //   ^     positions[0 % 3] == 0
    //    ^    positions[1 % 3] == 1
    //     ^   positions[2 % 3] == -1
    //      ...

    public char[][] solve(String str) {
        char[][] result = new char[3][str.length()];
        int[] positions = new int[] {1, 0, 1, 2};
        for (int i = 0; i < str.length(); i++) {
            result[positions[i % 4]][i] = str.charAt(i);
        }
        return result;
    }

    // Solution:
    //    1        5       9
    //    e        _       l
    //  H   l    o   W   r   d
    //         l       o       !
    // Given the example we can clearly see
    // that each 4 starting at 1 we fill the first line
    // each 2 starting at 0 the second line
    // each 4 starting at 3 the last line
    public String solveAsString(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < str.length(); i += 4) sb.append(str.charAt(i));
        for (int i = 0; i < str.length(); i += 2) sb.append(str.charAt(i));
        for (int i = 3; i < str.length(); i += 4) sb.append(str.charAt(i));
        return sb.toString();
    }

    public static void main(String[] args) {
        SinusoidString ss = new SinusoidString();
        for (String s : Arrays.asList(
            "Hello World!",
            "Hey Arnold"
        )) {
            System.out.println(ss.solveAsString(s));
//            Arrays.stream(ss.solveAsString(s))
//                .map(Arrays::toString)
//                .forEach(System.out::println);
        }
    }

}
