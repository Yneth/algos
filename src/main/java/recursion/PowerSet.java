package recursion;

import java.util.*;

public class PowerSet {
    //  The power set of a set S is the set of all subsets of S, including both the empty set 0
    // and S itself. The powerset of {0,1, 2) is graphically illustrated in Figure 16.4.
    //  Write a function that takes asinput a set and returnsits power set.
    //
    // Solution:
    // 0
    // 1
    // 2
    // 0 1
    // 0 2
    // 1 2
    // 0 1 2
    //
    // 0 1 2
    // generate all without 0
    // generate all with 0
    //
    //    1 2
    //    generate all without 0 1
    //    generate all with    1
    //
    //        2
    //        generate all without 0 1 2
    //        genereate all with   2
    //
    //
    //    0 1 2
    //    generate all without 1
    //    genereate all with   1
    //
    //       0 2
    //       generate all without 1 2
    //       generate all with

    private static List<List<Integer>> gen(int i,
                                           List<Integer> vals,
                                           List<Integer> positions,
                                           List<List<Integer>> powerset) {
        if (i >= vals.size()) {
            powerset.add(new ArrayList<>(positions));
            return powerset;
        }
        // generate all with vals[i]
        positions.add(vals.get(i));
        gen(i + 1, vals, positions, powerset);

        // generate all without vals[i]
        positions.remove(positions.size() - 1);
        gen(i + 1, vals, positions, powerset);

        return powerset;
    }

    public static List<List<Integer>> gen(List<Integer> values) {
        return gen(0, values, new ArrayList<>(values.size()), new ArrayList<>());
    }

    public static void main(String[] args) {
        System.out.println(gen(Arrays.asList(0, 1, 2)));
    }
}
