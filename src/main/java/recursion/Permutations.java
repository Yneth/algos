package recursion;

import java.util.*;

public class Permutations {
    //  This problem is concerned with computing all permutations of an array. For example,
    // if the array is(2,3,5, 7} one output could be (2,3,5, 7), (2,3,7,5), (2,5,3, 7), (2,5, 7,3),
    // <2,7,3,5>, <2,7,5,3>, <3,2,5,7>, <3,2,7,5>, <3,5,2,7>, <3,5,7,2>, <3,7,2,5>, <3,7,5,2>,
    // <5, 2,3, 7>, (5,2,7,3}, <5,3,2,7>, <5,3,7,2>, <5,7,2,3>, <5,7,2,3>, <7,2,3,5>, <7,2,5,3>,
    // <7,3, 2,5), <7,3,5, 2), <7,5, 2,3), <7,5,3, 2). (Any other ordering is acceptable too.)
    //  Write a program which takes as input an array of distinct integers and generates all
    // permutations of that array. No permutation of the array may appear more than once
    //
    // Example:
    // 1 2 3
    // 1
    // 2
    // 3
    // 1 2
    // 1 3
    // 2 1
    // 2 3
    // 3 1
    // 3 2
    // 1 2 3
    // 1 3 2
    // 2 1 3
    // 2 3 1
    // 3 1 2
    // 3 2 1
    // pseudocode:
    //  permutations(alphabet, chars)
    //    if alphabet == {}
    //      return chars
    //    list = []
    //    for a in alphabet
    //      list.add(permutations(alphabet - a, chars + a))
    //    return list


    private static List<List<Integer>> get(List<Integer> alphabet,
                                           List<Integer> word,
                                           List<List<Integer>> result) {
        if (alphabet.size() == 0) {
            result.add(word);
            return result;
        }
        for (int i = 0; i < alphabet.size(); i++) {
            List<Integer> nextAlphabet = new ArrayList<>(alphabet);
            nextAlphabet.remove(i);

            List<Integer> nextWord = new ArrayList<>(word);
            nextWord.add(alphabet.get(i));

            get(nextAlphabet, nextWord, result);
        }
        return result;
    }

    public static List<List<Integer>> get(List<Integer> alphabet) {
        return get(alphabet, new ArrayList<>(), new ArrayList<>());
    }

    private static List<List<Integer>> fast(int i, List<Integer> word, List<List<Integer>> result) {
        if (i == word.size() - 1) {
            result.add(new ArrayList<>(word));
        } else {
            for (int j = i; j < word.size(); j++) {
                System.out.println(i);
                Collections.swap(word, i, j);
                fast(i + 1, word, result);
                Collections.swap(word, i, j);
            }
        }
        return result;
    }

    public static List<List<Integer>> fast(List<Integer> xs) {
        return fast(0, xs, new ArrayList<>());
    }

    public static void main(String[] args) {
        System.out.println(fast(Arrays.asList(1, 2, 3)));
    }
}
