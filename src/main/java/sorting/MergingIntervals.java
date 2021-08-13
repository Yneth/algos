package sorting;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MergingIntervals {
    //  Suppose the time during the day that a person is busy is stored as a set of disjoint
    // time intervals. If an event is added to the person's calendar, the set of busy times may
    // need to be updated.
    //  In the abstract, we want a way to add an interval to a set of disjoint intervals and
    // represent the new set as a set of disjoint intervals. For example, if the initial set of
    // intervals is[-4,-1],[0, 2],[3,6],[7,9],[11,12],[14,17], and the added interval is[1,8],
    // the result is[-4,-1],[0, 9],[11,12],[14,17].
    //  Write a program which takes asinput an array of disjoint closed intervals with integer
    // endpoints, sorted by increasing order of left endpoint, and an interval to be added,
    // and returns the union of the intervalsin the array and the added interval. Your result
    // should be expressed as a union of disjoint intervalssorted by left endpoint.
    //
    // Example:
    // (-4,-1),(0,2),(3,6),(7,9),(11,12)
    //  (1,8)
    //  ^        ^     -     ^
    //    result pair would be (min(0,1),max(8,9)) == (0,9)
    // can we do it in place?
    //   yeah
    //

    static class Interval {
        int start;
        int end;

        public Interval(int s, int e) {
            this.start = s;
            this.end = e;
        }

        // ^     x             x       ^
        //
        // x     ^             x       ^
        //
        // ^     x             ^       x

        public boolean intersects(Interval other) {
            return !(
                (this.start > other.end && this.end > other.start)
                    || (this.start < other.end && this.end < other.start)
            );
        }

        public String toString() {
            return "(" + start + "," + end + ")";
        }
    }

    private static final Comparator<Interval> CMP = (i0, i1) -> {
        return Integer.compare(i0.start, i1.start);
    };

    public static List<Interval> merge(List<Interval> intervals, Interval toAdd) {
        Collections.sort(intervals, CMP);
        Interval start = null;
        int writeIdx = 0;
        for (int i = 0; i < intervals.size() && writeIdx < intervals.size(); i++) {
            Interval current = intervals.get(i);
            if (start == null && current.intersects(toAdd)) {
                start = current;

                while (i + 1 < intervals.size() && toAdd.intersects(intervals.get(++i))) {
                }
                i--;
                intervals.set(writeIdx++, new Interval(
                    Math.min(toAdd.start, start.start),
                    Math.max(toAdd.end, intervals.get(i).end)
                ));
            } else {
                intervals.set(writeIdx++, current);
            }
        }
        return intervals.subList(0, writeIdx);
    }

    public static void main(String[] args) {
        System.out.println(merge(
            Arrays.asList(
                new Interval(-4, -1),
                new Interval(0, 2),
                new Interval(3, 6),
                new Interval(7, 9),
                new Interval(11, 12)
            ),
            new Interval(1, 8)
        ));

        System.out.println(merge(
            Arrays.asList(
                new Interval(-4, -1),
                new Interval(0, 2),
                new Interval(3, 6),
                new Interval(7, 9),
                new Interval(11, 12)
            ),
            new Interval(12, 30)
        ));

        System.out.println(merge(
            Arrays.asList(
                new Interval(-4, -1),
                new Interval(0, 2),
                new Interval(3, 6),
                new Interval(7, 9),
                new Interval(11, 12)
            ),
            new Interval(-8, -1)
        ));

        System.out.println(merge(
            Arrays.asList(
                new Interval(-4, -1),
                new Interval(0, 2),
                new Interval(3, 6),
                new Interval(7, 9),
                new Interval(11, 12)
            ),
            new Interval(-8, -7)
        ));
    }

}
