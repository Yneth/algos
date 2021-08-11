package sorting;

import java.util.*;

public class RenderCalendar {
    //  Consider the problem of designing an online calendaring application. One compo¬
    // nent of the design is to render the calendar, i.e., display it visually.
    // Suppose each day consists of a number of events, where an event is specified as
    // a start time and a finish time. Individual events for a day are to be rendered as
    // nonoverlapping rectangular regions whose sides are parallel to the X- and Y-axes.
    // Let the X-axis correspond to time. If an event starts at time b and ends at time e, the
    // upper and lower sides of its corresponding rectangle must be at b and e,respectively.
    // Figure 14.1 represents a set of events.
    // Suppose the Y-coordinates for each day's events must lie between 0 and L (a pre¬
    // specified constant), and each event'srectangle must have the same "height" (distance
    // between the sidesparallel to the X-axis). Your task isto compute the maximum height
    // an event rectangle can have. In essence, thisis equivalent to the following problem
    //   Write a program that takes a set of events, and determines the maximum number of
    // events that take place concurrently.
    // Example:
    //                        -x
    //        --------x     -----x
    //    -----x  -------x  -x -------x
    //    ---------x  ---------x --x -x
    // result 4                ^
    //
    //         ^   ^  ^  ^   ^2^ ^ ^ 2^
    //
    //         ^   -  -  x
    //                       ^2^
    //
    //
    // Solution:
    //  sort by the end
    //
    // at least we can do brute force
    // for each event look other events in range
    // and do the counting
    //
    // But the real solution is to map events to
    // endpoints.
    // Sorting will have the following rules:
    // - If endpoint is start and equal to
    // end, place it first.
    // - Otherwise sort by time
    //
    // Then iterate over sorted endpoints
    // with two counters. Max num of simultaneous
    // intervals, and current simultaneous intervals
    //
    // Test:
    //      ----------
    //  ------- -
    //  -- --------
    //
    //  ^x ^  x t x  x
    //

    static class Event {
        int start, end;
        public Event(int s, int e) {
            this.start = s;
            this.end = e;
        }
    }

    static class Endpoint {
        int time;
        boolean isStart;
        public Endpoint(int t, boolean s) {
            this.time = t;
            this.isStart = s;
        }
        static final Comparator<Endpoint> CMP = (e0, e1) -> {
            if (e0.time == e1.time)
                return e0.isStart ? -1 : (e1.isStart ? 1 : 0);
            return Integer.compare(e0.time, e1.time);
        };
    }

    public static int find(List<Event> events) {
        List<Endpoint> points = new ArrayList<>();
        for (Event e : events) {
            points.add(new Endpoint(e.start, true));
            points.add(new Endpoint(e.end, false));
        }
        Collections.sort(points, Endpoint.CMP);
        int maxSimultaneousCount = 0;
        int currSimultaneousCount = 0;
        for (Endpoint e : points) {
            if (e.isStart) {
                currSimultaneousCount++;
                maxSimultaneousCount = Math.max(currSimultaneousCount, currSimultaneousCount);
            } else {
                currSimultaneousCount--;
            }
        }
        return maxSimultaneousCount;
    }
    // ----------------
    // ------
    //     --------
    //          ----------------

    public static void main(String[] args) {
        System.out.println(
            find(
                Arrays.asList(
                    new Event(0, 100),
                    new Event(0, 35),
                    new Event(30, 80),
                    new Event(50, 120)
                )
            )
        );

        System.out.println(
            find(
                Arrays.asList(
                    new Event(0, 1),
                    new Event(1, 2),
                    new Event(2, 3)
                )
            )
        );

        System.out.println(
            find(
                Arrays.asList(
                    new Event(0, 1),
                    new Event(10, 20)
                )
            )
        );

        System.out.println(
            find(
                Arrays.asList(
                )
            )
        );
    }

}
