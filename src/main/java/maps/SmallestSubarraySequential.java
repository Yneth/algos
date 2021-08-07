package maps;

public class SmallestSubarraySequential {
    // FIND SMALLEST SUBARRAY SEQUENTIALLY COVERING ALL VALUES
    //    In Problem 13.7 on Page 218 we did not differentiate between the order in which
    // keywords appeared. If the digest has to include the keywords in the order in which
    // they appear in the search textbox, we may get a different digest. For example, for the
    // search keywords "Union" and "save", in that order, the digest would be "Union, and
    // is not either to save".
    //     Write a program that takes two arrays of strings, and return the indices of the start¬
    // ing and ending index of a shortest subarray of the first array (the "paragraph"
    // array) that "sequentially covers", i.e., contains all the strings in the second array
    // (the "keywords" array), in the order in which they appear in the keywords array.
    // You can assume all keywords are distinct. For example, let the paragraph array be
    // (apple,banana,cat,apple), and the keywords array be (banana,apple). The para¬
    // graph subarray starting at index 0 and ending at index 1 does not fulfill the speci¬
    // fication, even though it contains all the keywords, since they do not appear in the
    // specified order. On the other hand, the subarray starting at index 1 and ending at
    // index 3 doesfulfill the specification.
    //     Hint: For each index in the paragraph array, compute the shortestsubarray ending at that index
    // which fulfills the specification.
    // Examples:
    //  -  "a","b","a","b","a","c"     a,b,c
    //      ^---^---------------^
    //              ^---^-------^      result
    //
    // Solution:
    //  - brute force:
    //
}
