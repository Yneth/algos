package greedy;

import java.util.*;

public class IntervalCovering {
    //  Consider a foreman responsible for a number of tasks on the factory floor. Each task
    // starts at a fixed time and ends at a fixed time. The foreman wants to visit the floor to
    // check on the tasks. Your job is to help him minimize the number of visits he makes.
    // In each visit, he can check on all the tasks taking place at the time of the visit. A visit
    // takes place at a fixed time, and he can only check on taskstaking place at exactly that
    // time. For example, if there are tasks at times [0,3],[2,6],[3,4],[6,9], then visit times
    // 0, 2,3,6 cover all tasks. A smaller set of visit times that also cover all tasks is 3,6. In
    // the abstract, you are to solve the following problem.
    //  You are given a set of closed intervals. Design an efficient algorithm for finding a
    // minimum sized set of numbers that covers all the intervals.
    //
    // Assumption:
    // so we need to find smallest possible number of task interval intersections
    //
    // Example:
    // (0,3),(2,6),(3,4),(6,9)
    // 0000
    //   11111
    //    22
    //       3333
    //   [3,6]
    //
    // convert task to endpoints (time, taskNumber, start?)
    // sort by time in case of overalap, start go first
    // iterate maintaining counter of max events at a time
    // as soon as the counter drops
    // drop the current index

    // mantain a set of covered tasks
    // Test:
    // (0,0,t) (2,1,t) (3,2,t) (3,0,f) (4,2,f) (6,3,t) (6,1,f) (9,3,f)
    //   {0}    {0,1}   {0,1,2}  {0,1,2}
    //                           ^
    //                                 {0,1,2}  {0,1,2,3} ^

    // (1,6)(2,3)(3,4)
    // {0} {0,1} {0,1,2}
    //             ^
    // (1,6)(1,4)(2,3)
    //

    static class Task {
        int start, end;

        public Task(int s, int e) {
            this.start = s;
            this.end = e;
        }
    }

    static class Endpoint implements Comparable<Endpoint> {
        int time, id;
        boolean isStart;

        public Endpoint(int t, int i, boolean s) {
            this.time = t;
            this.id = i;
            this.isStart = s;
        }

        public int compareTo(Endpoint that) {
            int timeCmp = Integer.compare(this.time, that.time);
            int isStartCmp = Boolean.compare(that.isStart, this.isStart);
            return timeCmp != 0 ? timeCmp : isStartCmp;
        }
    }

    public static List<Integer> find(List<Task> tasks) {
        PriorityQueue<Endpoint> endpoints = new PriorityQueue<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            endpoints.add(new Endpoint(t.start, i, true));
            endpoints.add(new Endpoint(t.end, i, false));
        }
        List<Integer> visitTimes = new ArrayList<>();
        Set<Integer> coveredTasks = new HashSet<>();
        while (!endpoints.isEmpty()) {
            Endpoint e = endpoints.remove();
            if (e.isStart) {
                coveredTasks.add(e.id);
            } else if (coveredTasks.contains(e.id)) {
                visitTimes.add(e.time);
                coveredTasks.clear();
            }
        }
        return visitTimes;
    }

    //(0,3),(2,6),(3,4),(6,9)
    // (1,6)(2,3)(3,4)
    // (1,6)(1,4)(2,3)
    public static void main(String[] args) {
        System.out.println(
            find(Arrays.asList(
                new Task(0, 3),
                new Task(2, 6),
                new Task(3, 4),
                new Task(6, 9)
            ))
        );

        System.out.println(
            find(Arrays.asList(
                new Task(1, 6),
                new Task(2, 3),
                new Task(3, 4)
            ))
        );

        System.out.println(
            find(Arrays.asList(
                new Task(1, 6),
                new Task(1, 4),
                new Task(2, 3)
            ))
        );
    }
}
