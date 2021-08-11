package maps;

import java.util.*;

public class StringDecompositions {

    // COMPUTE ALL STRING DECOMPOSITIONS
    //     This problem is concerned with taking a string (the "sentence" string) and a set
    // of strings (the "words"), and finding the substrings of the sentence which are the
    // concatenation of all the words (in any order). For example, if the sentence string
    // is "amanaplanacanal" and the set of words is {"can","apl","ana"), "aplanacan" is a
    // substring of the sentence that is the concatenation of all words.
    //     Write a program which takes as input a string (the "sentence") and an array of strings
    // (the "words"), and returns the starting indices of substrings of the sentence string
    // which are the concatenation of all the strings in the words array. Each string must
    // appear exactly once, and their ordering isimmaterial. Assume all strings in the words
    // array have equal length. It is possible for the words array to contain duplicates.
    //
    // Example:
    //  amanplanacanal    can apl ana
    /// ^--
    //   ^--
    //    ^-- match one
    //       ^-- match second
    //          ^-- match third
    //             add 2 to result
    //     ^--
    //      ^--
    //         ...
    //  aabbccddaa         aa bb dd
    //  ^- match
    //    ^- match
    //      ^- fail
    //   ^- match
    //    ^-
    //     ^-
    //      ...
    // if one fails does it mean the other one will fail too at current region?
    //
    // Solution:
    // init a map of frequencies
    // for i in len(sentence)
    //   if allWords(i)
    //     result.add(i)
    //
    // allWords(i)
    //   for

    private static boolean allMatch(int idx, String sentence, Set<String> words, int k) {
        Map<String, Integer> occurrences = new HashMap<>();
        for (int i = idx; i <= sentence.length() - k; i += k) {
            String sub = sentence.substring(i, i + k);
            if (words.contains(sub)) {
                System.out.println(sub);
                occurrences.put(sub, occurrences.getOrDefault(sub, 0) + 1);
                System.out.println(occurrences.size() == words.size());
                if (occurrences.size() == words.size()) return true;
            } else {
                return false;
            }
        }
        return occurrences.size() == words.size();
    }

    public static List<Integer> find(String sentence, Set<String> words) {
        List<Integer> result = new ArrayList<>();
        int unitSize = words.iterator().next().length();
        for (int i = 0; i < sentence.length() - unitSize; i++) {
            if (allMatch(i, sentence, words, unitSize)) {
                result.add(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(
            find(
                "amanaplanacanaaplcan",
                new HashSet<>(Arrays.asList("can","apl","ana"))
            )
        );
        System.out.println(
            find("test", new HashSet<>(Arrays.asList("aaa")))
        );

        System.out.println(
            find("testtest", new HashSet<>(Arrays.asList("te", "st")))
        );
    }
}
