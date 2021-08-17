package recursion;

import java.util.*;

public class AllSubsetsOfSizeK {
    //  There are a number of testing applications in which it is required to compute all
    // subsets of a given size for a specified set.
    //  Write a program which computes all size k subsets of {1, 2,...,nj, where k and n are
    // program inputs. For example, if k = 2 and n = 5, then the result is the following:
    // ((1, 2), (1,3),(1,4),(1,5), (2,3),(2,4),(2,5), (3,4),(3,5), (4,5))
    //
    // Example:
    // 1,2,3,4,5
    // k = 2
    // 1,2 1,3 1,4 1,5
    // 2,3 2,4 2,5
    // 3,4 3,5
    // 4,5
    //
    // generate(vals, i, k, acc, res)
    //   if (k == 0) res.add(acc) return;
    //   if (i == len(vals)) return;
    //   generate(vals, i + 1, k, acc, res)
    //   for j in i..len(vals)
    //      generate(vals, i + 1, k - 1, acc.with(vals[j]), res)
    //
    //  1,2,3   k=2
    //  1,2 1,3 2,3
    //
    //   generate([1,2,3], 0, 2, [], [])
    //     generate([1,2,3], 1, 1, [1], [])
    //      generate(...,     2, 0, [1, 2],[])
    //      genreate(...,    2, 0, [1, 3], [])
    //     generate(..., 1, 2, [], [])
    //       generate(..., 2, 1, [2], [])
    //        genreate(.., 3, 1, [2, 3])

    private static List<List<Integer>> subsets(List<Integer> vals, int i, int k,
                                               List<Integer> acc,
                                               List<List<Integer>> res) {
        if (acc.size() == k) {
            res.add(new ArrayList<>(acc));
            return res;
        }
        if (i == vals.size()) return res;

        for (int j = i; j < vals.size(); j++) {
            acc.add(vals.get(j));
            subsets(vals, j + 1, k, acc, res);
            acc.remove(acc.size() - 1);
        }
        return res;
    }

    public static List<List<Integer>> subsets(List<Integer> vals, int k) {
        return subsets(vals, 0, k, new ArrayList<>(), new ArrayList<>());
    }

    public static void main(String[] args) {
        System.out.println(subsets(Arrays.asList(1, 2, 3, 4), 3));
    }
}
