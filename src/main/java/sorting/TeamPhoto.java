package sorting;

import java.util.*;

public class TeamPhoto {
    //  You are a photographer for a soccer meet. You will be taking pictures of pairs of
    // opposing teams. All teams have the same number of players. A team photo consists
    // of a front row of players and a back row of players. A player in the back row must
    // be taller than the player in front of him, asillustrated in Figure 14.3. All players in a
    // row must be from the same team
    // Solution:
    // sort both teams by height
    // put team with a higher total height in the first row

    private static void sortByHeight(List<Integer> l) {
        l.sort((h0, h1) -> Integer.compare(h0, h1));
    }

    public static List<List<Integer>> compute(List<Integer> a, List<Integer> b) {
        sortByHeight(a);
        sortByHeight(b);

        boolean greater = false;
        boolean allEqualOrOp = true;
        for (int i = 0; i < a.size(); i++) {
            int cmp = a.get(i).compareTo(b.get(i));
            if (i == 0 && cmp > 0) greater = true;

            if (cmp < 0 && greater || cmp > 0 && !greater) {
                allEqualOrOp = false;
                break;
            }
        }
        if (!allEqualOrOp) return null;

        return greater
            ? Arrays.asList(a, b)
            : Arrays.asList(b, a);
    }

    public static void main(String[] args) {
        System.out.println(compute(
            Arrays.asList(1, 2, 3, 4, 5, 6),
            Arrays.asList(6, 7, 8, 4, 5, 6)
        ));

        System.out.println(compute(
            Arrays.asList(1, 2, 3, 4, 5, 100),
            Arrays.asList(6, 7, 8, 4, 5, 6)
        ));

        System.out.println(compute(
            Arrays.asList(6, 7, 8, 4, 5, 6),
            Arrays.asList(1, 2, 3, 4, 5, 100)
        ));

        System.out.println(compute(
            Arrays.asList(1, 1, 1),
            Arrays.asList(0, 0, 1)
        ));

        System.out.println(compute(
            Arrays.asList(0, 0, 1),
            Arrays.asList(1, 1, 1)
        ));
    }

}
