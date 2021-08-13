package sorting;

import java.util.*;

public class UnionIntervals {
    // In this problem we consider sets of intervals with integer endpoints; the intervals
    //may be open or closed at either end. We want to compute the union of the intervals
    //in such sets. A concrete example is given in Figure 14.2.
    // Design an algorithm that takes as input a set of intervals, and outputs their union
    //expressed as a set of disjoint intervals
    //
    // Solution:
    // represent each interval as two endpoints
    // sort
    // then iterate over endpoints and as soon as there is a gap between current and next
    // endpoint drop
    //
    // not a solution
    //
    // Example:
    //     -------      -----          -   -
    // ------ -  ----          ------    -
    //
    // sort by start
    // ^         x
    //        -
    // ^            x   drop
    //                  ^   x drop
    //                         ^    x

    static class Interval {
        int start, end;
        public Interval(int s, int e) {
            this.start = s;
            this.end = e;
        }
        public boolean intersects(Interval other) {
            return !(
                (this.start > other.end && this.end > other.start)
                || (this.start < other.end && this.end < other.start)
            );
        }
        public String toString() {
            return "(" + start + "," + end +")";
        }
    }

    private static final Comparator<Interval> START_CMP = (i0, i1) -> {
        return Integer.compare(i0.start, i1.start);
    };

    public static List<Interval> union(List<Interval> intervals) {
        Collections.sort(intervals, START_CMP);
        List<Interval> result = new ArrayList<>();
        Interval current = null;
        for (int i = 0; i < intervals.size(); i++) {
            Interval ii = intervals.get(i);
            if (current == null) current = ii;
            else if (current.intersects(ii)) {
                current = new Interval(
                    Math.min(current.start, ii.start),
                    Math.max(current.end, ii.end)
                );
            } else /* if (!current.intersects(ii)) */ {
                result.add(current);
                current = ii;
            }
            if (i == intervals.size() - 1) result.add(current);
        }
        return result;
    }

    //     -------      -----          -   -
    // ------ -  ----          ------    -
    // 0   56 8  9  12  14  15 17  18  20 22 23
    public static void main(String[] args) {
        System.out.println(union(
            Arrays.asList(
                new Interval(0,6),
                new Interval(5,9),
                new Interval(8, 8),
                new Interval(9, 12),
                new Interval(14, 15),
                new Interval(17, 18),
                new Interval(17, 18),
                new Interval(20, 20),
                new Interval(22, 22),
                new Interval(23, 23)
            )
        ));

        System.out.println(union(
            Arrays.asList(
                new Interval(0,6)
            )
        ));

        System.out.println(union(
            Arrays.asList(
                new Interval(0,6),
                new Interval(5,9),
                new Interval(8, 8),
                new Interval(9, 12),
                new Interval(14, 15),
                new Interval(17, 18),
                new Interval(17, 18),
                new Interval(20, 20),
                new Interval(22, 22),
                new Interval(23, 23),
                new Interval(0, 50)
            )
        ));
    }

}
