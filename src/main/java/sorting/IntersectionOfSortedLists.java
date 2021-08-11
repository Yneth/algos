package sorting;

import java.util.*;

public class IntersectionOfSortedLists {
    // COMPUTE THE INTERSECTION OF TWO SORTED ARRAYS
    //   A natural implementation for a search engine isto retrieve documentsthat match the
    // set of wordsin a query by maintaining an inverted index. Each page is assigned an
    // integer identifier, its document-ID. An inverted index is a mapping that takes a word
    // w and returns a sorted array of page-ids which contain w—the sort order could be,
    // for example, the page rank in descending order. When a query contains multiple
    // words, the search engine findsthe sorted array for each word and then computesthe
    // intersection of these arrays—these are the pages containing all the wordsin the query.
    // The most computationally intensive step of doing this is finding the intersection of
    // the sorted arrays.
    //   Write a program which takes as input two sorted arrays, and returns a new array
    // containing elements that are present in both of the input arrays. The input arrays
    // may have duplicate entries, but the returned array should be free of duplicates. For
    // example, the input is (2,3,3,5,5,6,7,7,8,12} and (5,5,6,8,8,9,10,10), your output
    // should be (5,6,8).
    //
    // Example:
    // 1 2 3 4 7 7 7 7 8
    //                 ^
    // 8
    // ^
    //
    // Solution:
    //  keep two cursors
    //  init result set
    //  if left == right: add to set
    //  else if left < right: left++
    //  else                  right++
    // return set
    //
    // Small improvement
    // if one of the arrays starts from very big value
    // and the other is from small
    // or
    // if one array is significantly larger
    //
    // then we can apply binary search for each value of the
    // smaller array to find if its in the first one, resulting in k log n
    //

    public static List<Integer> find(List<Integer> a, List<Integer> b) {
        List<Integer> sm = a, bg = b;
        if (a.size() > b.size()) {
            sm = b;
            bg = a;
        }

        int l = 0, r = 0;
        int n = sm.size();
        List<Integer> result = new ArrayList<>();
        while (l < sm.size() && r < bg.size()) {
            System.out.println(sm.get(l) + "   " + bg.get(r));
            int cmp = Integer.compare(sm.get(l), bg.get(r));
            if (cmp < 0) l++;
            else if (cmp > 0) r++;
            else {
                int val = sm.get(l);
                result.add(sm.get(l));
                while (l < sm.size() && sm.get(l) == val) l++;
                while (r < bg.size() && bg.get(r) == val) r++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(find(
            Arrays.asList(1, 2, 3, 3, 8, 9),
            Arrays.asList(3, 9)
        ));
    }


}
