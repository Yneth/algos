package dp;

import java.util.*;

public class IsConcatenation {
    //  Suppose you are designing a search engine. In addition to getting keywords from
    // a page's content, you would like to get keywords from Uniform Resource Locators
    // (URLs). For example, bedbathandbeyond.com yieldsthe keywords "bed, bath, beyond,
    // bat, hand": the first two coming from the decomposition "bed bath beyond" and the
    // latter two coming from the decomposition "bed bat hand beyond".
    //  Given a dictionary i.e., a set of strings, and a name, design an efficient algorithm that
    // checks whether the name is the concatenation of a sequence of dictionary words. If
    // such a concatenation exists, return it. A dictionary word may appear more than once
    // in the sequence, e.g., "a", "man", "a", "plan", "a", "canal" is a valid sequence for
    // "amanaplanacanal".
    // Assumptions:
    //   Given:
    //     word 0 < n <
    //     dictionary: set<string>
    //   Return:
    //     boolean, true if is concatenation of all words at least once
    //
    // Assumptions:
    //   is it a concatenation or it should just contain all words?
    //    concatenation strictly
    // Example:
    //  word:   bedbathbeyond
    //  dict:   beyond, bed, bath
    //
    // Solution:
    //  Brutefoce;
    //    iterate over the whole string
    //    looking for all string matches
    //    maintaining a map of counts for each word in a dictionary
    //    may also need to track index of first char of each word
    //    to see overlaps
    //
    //  DP:
    //    given word[i:]
    //      look for each word at word[i:j]
    //
    // Had to read the solution
    //  it has the following idea:
    //
    //  iterate over word chars
    //   at each step check if word[0:i] is in dict
    //     if yes, store length of the match
    //     otherwise check word[j:i]
    //       same with index
    //   now we have array of matched dict word sizes
    //   if the last entry of this array is non FLAG (empty)
    //   check

    public static List<String> find(String domain, Set<String> dict) {
        int[] lastLength = new int[domain.length()];
        Arrays.fill(lastLength, -1);

        for (int i = 0; i < domain.length(); i++) {
            if (dict.contains(domain.substring(0, i + 1))) {
                lastLength[i] = i + 1;
            } else {
                for (int j = 0; j < i; j++) {
                    if (lastLength[j] != -1 && dict.contains(domain.substring(j + 1, i + 1))) {
                        lastLength[i] = i - j;
                        break;
                    }
                }
            }
        }
        List<String> result = new ArrayList<>();
        if (lastLength[lastLength.length - 1] != -1) {
            int idx = domain.length() - 1;
            while (idx >= 0) {
                result.add(domain.substring(idx + 1 - lastLength[idx], idx + 1));
                idx -= lastLength[idx];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(
            find("amanaplanacanal", new HashSet<>(Arrays.asList("man", "canal", "plan")))
        );
    }
}
