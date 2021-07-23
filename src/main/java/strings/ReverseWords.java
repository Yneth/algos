package strings;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ReverseWords {
    public static String[] reverse(String[] strings) {
        for (int i = 0; i < strings.length / 2; i++) {
            String tmp = strings[i];
            strings[i] = strings[strings.length - i - 1];
            strings[strings.length - i - 1] = tmp;
        }
        return strings;
    }

    public String solve(String wordsStr) {
        String[] words = wordsStr.split(" ");
        reverse(words);
        return Arrays.stream(words)
            .collect(Collectors.joining(" "));
    }

    public static void main(String[] args) {
        ReverseWords rw = new ReverseWords();
        System.out.println(rw.solve("test alabama"));
    }
}
