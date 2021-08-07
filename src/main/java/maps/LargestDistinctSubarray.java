package maps;

import java.util.*;

public class LargestDistinctSubarray {
    // FIND THE LONGEST SUBARRAY WITH DISTINCT ENTRIES
    //     Write a program that takes an array and returns the length of a longest subarray
    // with the property that all its elements are distinct. For example, if the array is
    // (/,s,/,e,t,w,e,n,w,e) then a longest subarray all of whose elements are distinct is
    // (s,f,e,t,w).
    //     Hint: What should you do if the subarray from indices i to j satisfies the property, but the
    // subarray from i to j +1does not?
    //
    // Examples:
    // a b c b c a f
    //       --------
    // ^ ^
    // ^   ^
    // ^     ^ -
    //   ^   ^ -
    //     ^ ^
    //     ^   ^ -
    //       ^ ^
    //       ^   ^
    //       ^     ^
    //  l m n a b c d f a
    // Solution:
    //  two indicies;
    //  iterate the right one as long as there are no dups
    //       otherwise increment the left one
    //   to keep track of duplicates we need to maintain a set
    // Do I need to write pseudocode?

    static class Subarray {
        int start;
        int end;
        public Subarray(int start, int end) {
            this.start = start;
            this.end = end;
        }
        public int dist() {
            return end - start;
        }

        @Override
        public String toString() {
            return "(" + start + "," + end + ")";
        }
    }

    public static Subarray find(List<String> strs) {
        Subarray result = new Subarray(0, -1);
        Set<String> words = new HashSet<>();
        words.add(strs.get(0));
        for (int i = 0, j = 1; i <= j && j < strs.size();) {
            if (!words.contains(strs.get(j))) {
                words.add(strs.get(j));
                if (result.end == -1) result.end = j;
                else if (result.dist() < j - i) result = new Subarray(i, j);
                j++;
            } else {
                while (words.contains(strs.get(j)))
                    words.remove(strs.get(i++));
            }
            System.out.println(words);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(
            find(Arrays.asList("a", "b", "c", "b", "c", "a", "f"))
        );
        System.out.println(
            find(Arrays.asList("l m n a b c d f a".split(" ")))
        );
        System.out.println(find(Arrays.asList("a a a b c a a a d d a".split(" "))));
        System.out.println(find(Arrays.asList("a b c d".split(" "))));
    }
}
