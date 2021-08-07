package maps;

import java.util.*;

public class SmallestSubarrayCoveringKeywords {
    // FIND THE SMALLEST SUBARRAY COVERING ALL VALUE
    // When you type keywordsin a search engine, the search engine willreturn results, and
    //each result contains a digest of the web page, i.e., a highlighting within that page of
    //the keywordsthat you searched for. For example, a search for the keywords "Union"
    //and "save" on a page with the text of the Emancipation Proclamation should return
    //the resultshown in Figure 13.1.
    // The digest for this page is the text in boldface, with the keywords underlined for
    //emphasis. It is the shortestsubstring of the page which contains all the keywordsin
    //the search. The problem of computing the digest is abstracted asfollows.
    // Write a program which takes an array of strings and a set of strings, and return the
    //indices of the starting and ending index of a shortestsubarray of the given array that
    //"covers" the set, i.e., contains all strings in the set.

    // Solution:
    // - Brute-force:
    //   create a map of all occurrences of all keywords
    //   then pick the smallest permutation of keyword placements
    //
    // - iterate over the paragrah
    //    each time we see a keyword
    //    update a map of frequencies
    //    also maintain the minmax indicies
    //
    //    LocalSubarray(kws, start, end):
    //      start_k = ""
    //      start_i = -1
    //      end_k = ""
    //      end_i = -1
    //
    //    if word in keywords
    //       freq[word] = freq[word] + 1
    //
    //    // subarray init
    //    if local_subarray.start == null
    //       local_subarray.start = [i, word]
    //    if local_subarray.end == null
    //       local_subarray.end = [i, word]
    //
    //    if len(freq) == len(keywords):
    //       subarray = local_subarray
    //       // subarray_update
    //       if local_subarray
    //
    // Example:
    //  a b aa c bb c a b         q=[abc]
    //  ^------^    ^---^
    //
    // what if we knew frequencies beforehand
    //
    //  ^ ^    ^
    //
    // Wow, so it is basically about two pointers that have a subarray
    // a,b,aa,c,bb,c,a,b    q=[a,b,c]
    // ^------^           covers len 4
    //   ^----^
    //   ^------^
    //   ^---------^
    //   ^-----------^    covers len 6
    //      ^----------^  covers but we are shrinking now
    //        ^--------^
    //           ^-----^
    //             ^---^ covers
    //               ^-^ stop
    //
    // a is in q;  inc a
    //   b is in q; inc b
    //     x
    //        c is in q; inc c; we have size of len(q) init subarray
    //
    // we need a map of moving frequencies
    //
    // Do I need to proceed with pseudocode? 20 min already...
    //   yes; ok
    // pseudocode:
    //   freq = {}
    //   subarray = [-1, -1]
    //   if p[i] in q: {inc(freq[p[i]]); if subarra[0] == -1: subarray[0] = i    }
    //   for i = 0, j = 1; i < j, j < len;
    //     if p[j] in q: {inc(freq[p[j]]) ...  }
    //     while len(freq) == len(q):
    //       subarray = [subarray[0], j]
    //       if p[i] in q: dec(freq[p[i]])
    //       if frep[p[i]] == 0: rm()
    //       i++
    //     else
    //        j++

    public static List<Integer> find(List<String> paragraph, Set<String> keywords) {
        Map<String, Integer> freq = new HashMap<>();

        List<Integer> subarray = new ArrayList<>();
        subarray.add(-1);
        subarray.add(-1);

        if (keywords.contains(paragraph.get(0))) {
            freq.put(paragraph.get(0), 1);
            subarray.set(0, 0);
        }
        for (int i = 0, j = 1; i < j && j < paragraph.size(); j++) {
            String rightWord = paragraph.get(j);
            if (keywords.contains(rightWord)) {
                freq.put(rightWord, freq.getOrDefault(rightWord, 0) + 1);
            }

            while (freq.size() == keywords.size()) {
                if (j - i < subarray.get(1) - subarray.get(0)
                    || (subarray.get(0) < 0 || subarray.get(1) < 0)) {

                    subarray.set(0, i);
                    subarray.set(1, j);
                }

                String leftWord = paragraph.get(i);
                if (keywords.contains(leftWord)) {
                    int count = freq.get(leftWord);

                    if (count == 1) freq.remove(leftWord);
                    else freq.put(leftWord, count - 1);
                }
                i++;
            }
        }
        if (subarray.get(0) == -1 || subarray.get(1) == -1) return null;
        return subarray;
    }

    public static void main(String[] args) {
        Set<String> kw = new HashSet<>();
        kw.add("a");
        kw.add("b");
        kw.add("c");
        System.out.println(
            find(Arrays.asList("a","b","aa","c","bb","c","a","b"), kw)
        );
        System.out.println(
            find(Arrays.asList("a","b","aa","c","bb","c","a"), kw)
        );
        System.out.println(
            find(Arrays.asList("a","b","b","c","bb","c","a"), kw)
        );
        System.out.println(
            find(Arrays.asList("a","a","b","b","c","c"), kw)
        );
    }

}
