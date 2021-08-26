package greedy;

import java.util.*;

public class ScheduleWithMinWaitingTime {
    //  A database hasto respond to a set of client SQL queries. The service time required for
    // each query is known in advance. For this application, the queries must be processed
    // by the database one at a time, but can be done in any order. The time a query waits
    // before its turn comesis called its waiting time.
    //  Given service timesfor a set of queries, compute a schedule for processing the queries
    // that minimizes the total waiting time. Return the minimum waiting time. For
    // example, if the service times are (2,5,1,3), if we schedule in the given order, the total
    // waiting time is0+(2)+(2+5)+(2+5+l) = 17. If however,weschedule queriesin order
    // of decreasing service times, the total waiting time is 0 + (5) + (5 + 3) + (5 + 3+ 2) = 23.
    // As we will see, for this example, the minimum waiting time is 10.
    //
    // 2 5 1 3
    // 0 2 7 8 = 17
    //
    // what if we processed in a sorted manner
    // 1  2  3     5
    // 0 (1) (1+2) (1+2+3) = 10
    //
    // is it true for all cases?
    //   seems like yes
    //
    public static int minTime(int[] arr) {
        Arrays.sort(arr);
        int time = 0;
        int acc = 0;
        for (int i = 1; i < arr.length; i++) {
            acc += arr[i - 1];
            time = time + acc;
        }
        return time;
    }

    public static void main(String[] args) {
        System.out.println(minTime(new int[] {5, 2, 1, 3}));
    }
}
