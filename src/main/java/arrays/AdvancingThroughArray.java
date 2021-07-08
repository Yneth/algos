package arrays;

import java.util.LinkedList;
import java.util.Queue;

public class AdvancingThroughArray {
    // at each step add all possible moves from current position to a queue
    // then check if we reached a solution
    // is it possible to get stuck?

    // tests:
    //   1 2 0 0 1
    //   q[2]
    //   q[0,0]
    //   q[] -- false
    //
    //   3 3 1 0 1
    //   q[3,1,0]
    //   q[1,0,1,0] -
    //   reached a solution at step 1
    //
    // such solution requires a pair
    // nope, we have an array

    public boolean isEndReachable(int[] arr) {
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        while (!q.isEmpty()) {
            Integer i = q.remove();
            if (i == arr.length - 1) return true;
            for (int j = i + 1; j <= i + arr[i]; j++) {
                q.add(j);
            }
        }
        return false;
    }

    // the idea here is to iterate over current max reach
    public boolean isEndReachable_fast(int[] arr) {
        int furthest = 0;
        for (int i = 0; i <= furthest && furthest < arr.length; i++) {
            System.out.println("i = " + i + ", max(" + furthest + "," + (i + arr[i]) + ")");
            furthest = Math.max(furthest, i + arr[i]);
        }
        return furthest >= arr.length - 1;
    }

    public static void main(String[] args) {
        AdvancingThroughArray ata = new AdvancingThroughArray();
        System.out.println(ata.isEndReachable(new int[]{3, 3, 1, 0, 1}));
        System.out.println(ata.isEndReachable(new int[]{1, 2, 0, 0, 1}));

        System.out.println(ata.isEndReachable_fast(new int[]{3, 3, 1, 0, 1}));
        System.out.println(ata.isEndReachable_fast(new int[]{1, 2, 0, 0, 1}));
    }
}
