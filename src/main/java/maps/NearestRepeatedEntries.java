package maps;

import java.util.*;
import java.util.stream.*;

public class NearestRepeatedEntries {

    // People do not like reading text in which a word is used multiple times in a short
    // paragraph. You are to write a program which helps identify such a problem.
    //
    // Write a program which takes as input an array and finds the distance between a
    // closest pair of equal entries. For example, if s =
    // ("All", "work", "and", "no", "play","makes", "for", "no", "work", "no", "fun", "and", "no", "results"), then the second
    //   0      1       2       3     4     5        6      7     8       9     10      11    12      13
    // and third occurrences of "no" is the closest pair.
    //
    // Solution:
    //  - collect word frequencies with distance between occurrences
    //    heap is not needed, just take all values, flatMap sort, and take the first one
    //
    //    to calculate distance just subtract last and first indices
    //
    // Test:
    // all - [0]
    // work - [1,8]
    // and - [2]
    // no - [3,7,9,12] 9-7
    // play [4]
    // makes [5]
    // for   [6]
    // fun   [10]
    // results [13]
    //
    // pseudocode:
    //  frequencies = {}
    //  ...

    public static String find(String[] words) {
        Map<String, List<Integer>> occurrences = new HashMap<>();
        int minDistance = Integer.MAX_VALUE;
        String min = null;
        for (int i = 0; i < words.length; i++) {
            List<Integer> e = occurrences.get(words[i]);
            if (e != null) {
                int distance = i - e.get(e.size() - 1);
                e.add(i);
                if (minDistance > distance) {
                    minDistance = distance;
                    min = words[i];
                }
            } else {
                List<Integer> newEntry = new ArrayList<>();
                newEntry.add(i);
                occurrences.put(words[i], newEntry);
            }
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(find(new String[]{
            "All", "work", "and", "no", "play", "makes", "for",
            "no", "work", "no", "fun", "and", "no", "results"
        }));
        System.out.println(find(new String[] {"a", "b"}));
        System.out.println(find(new String[] {"a", "b", "a", "b", "b"}));
    }

}
