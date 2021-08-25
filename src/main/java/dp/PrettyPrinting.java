package dp;

import java.util.*;

public class PrettyPrinting {
    //  Consider the problem of laying out text using a fixed width font. Each line can hold
    // no more than a fixed number of characters. Words on a line are to be separated by
    // exactly one blank. Therefore, we may be left with whitespace at the end of a line
    // (since the next word will not fit in the remaining space). This whitespace is visually
    // unappealing.
    //  Define the messiness of the end-of-line whitespace as follows. The messiness of a
    // single line ending with b blank characters is b2. The total messiness of a sequence of
    // lines is the sum of the messinesses of all the lines. A sequence of words can be split
    // across lines in different ways with different messiness, asillustrated in Figure 17.12.
    //  Given text, i.e., a string of words separated by single blanks, decompose the text into
    // lines such that no word is split across lines and the messiness of the decomposition
    // is minimized. Each line can hold no more than a specified number of characters.
    //
    // Are we given a string and the line length?
    //  yes, yes
    //
    // Examples:
    // This is a short line__
    // but this line will be_
    // longer. I'm the_______
    // shortest. Whenever you
    // think about it, it____
    // doesn't think about___
    // you.__________________
    //
    // Line length = 30
    //
    // Solution:
    // We need to guess the start of the next
    // line. So it is the prefix problem
    // When we pick a start of the next line
    // we can calculate the messiness of
    // the previous line. which is to be minimized.
    //
    // dp[i] = min(f(j - i) + dp[j] for j in i+1..n)
    // f(i) = count(i.)
    //
    // Test:
    // This is a short line but this line will be longer.
    // ^    ^
    // ^       ^
    // ^         ^
    // ^               ^
    //                      skip as is too long
    //       ^  ^
    // ...
    // let dp[0] = 0
    //

    static class Entry {
        int messiness;
        int idx;

        public Entry(int m, int i) {
            this.messiness = m;
            this.idx = i;
        }
        public Entry min(Entry that) {
            if (this.messiness < that.messiness) {
                return this;
            }
            return that;
        }
        public String toString() {
            return String.format("(%s,%s)", messiness, idx);
        }
    }

    private static int messiness(int lineLength, String word) {
        int blank = lineLength - word.length();
        return blank * blank;
    }

    private static List<List<Integer>> doPrettyPrint(List<String> words, int lineLength) {
        Entry[] entries = new Entry[words.size()];
        entries[0] = new Entry(messiness(lineLength, words.get(0)), 0);

        for (int i = 1; i < words.size(); i++) {
            int blank = lineLength - words.get(i).length();
            entries[i] = new Entry(entries[i - 1].messiness + blank * blank, i);

            for (int j = i - 1; j >= 0; --j) {
                blank = blank - (words.get(j).length() + 1);
                if (blank < 0) break;

                int currentMessiness = blank * blank;
                int firstJMessiness = j <= 0 ? 0 : entries[j - 1].messiness;

                entries[i] = entries[i].min(new Entry(currentMessiness + firstJMessiness, j));
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        int prevId = words.size() - 1;
        Entry e = entries[prevId];
        while (e.idx > 0) {
            result.add(Arrays.asList(e.idx, prevId));
            prevId = e.idx;
            e = entries[e.idx];
        }
        result.add(Arrays.asList(e.idx, prevId));
        return result;
    }

    public static String prettyPrint(List<String> words, int lineLength) {
        List<List<Integer>> ids = doPrettyPrint(words, lineLength);

        StringBuilder result = new StringBuilder();
        for (int i = ids.size() - 1; i >= 0; --i) {
            List<Integer> range = ids.get(i);

            for (String word : words.subList(range.get(0), range.get(1) + 1)) {
                result.append(word).append(" ");
            }
            result.deleteCharAt(result.length() - 1);
            result.append("\n");
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    public static void main(String[] args) {
        String text = "This is a short line\n"
            + "but this line will be\n"
            + "longer. I'm the\n"
            + "shortest. Whenever you\n"
            + "think about it, it\n"
            + "doesn't think about\n"
            + "you.";
        System.out.println(prettyPrint(
            Arrays.asList(text.split("\\s+")),
            30
        ));
    }
    // 6 13 15 8 12 6
    // 36 169 225

}
