package heaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class SortKIncreasingDecreasing {
    // Design an efficient algorithm for sorting a fc-increasing-decreasing array.
    // Example:
    // k = 2
    // 1 10 8 3 5 20 18 10
    //
    // Solution:
    // create a list of typed sublists
    // by typed I mean DECREASING or INCREASING
    // then proceed with MergeK algorithm
    // which is based on having a priority queue of
    // K list heads
    // Space: O(k)
    // Time: O(n log k)
    //

    static class ListIter implements Comparable<ListIter> {
        boolean increasing;
        int index;
        List<Integer> ref;
        public ListIter(List<Integer> ref, boolean increasing) {
            this.ref = ref;
            this.increasing = increasing;
            if (increasing) this.index = 0;
            else this.index = ref.size() - 1;
        }
        public Integer get() {
            return ref.get(index);
        }
        public ListIter next() {
            if (increasing) index++;
            else index--;

            if (index >= ref.size()) return null;
            if (index < 0) return null;

            return this;
        }

        @Override
        public int compareTo(ListIter that) {
            return Integer.compare(this.get(), that.get());
        }
    }

    public static List<Integer> compute(List<Integer> arr, int k) {
        PriorityQueue<ListIter> pq = new PriorityQueue<>();
        boolean increasing = true;
        for (int i = 0; i < (int) Math.ceil(arr.size() / (double )k); i++) {
            List<Integer> ref = arr.subList(i * k, Math.min(arr.size(), i * k + k));
            pq.add(new ListIter(ref, increasing));
            increasing = !increasing;
        }

        List<Integer> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            ListIter iter = pq.remove();
            result.add(iter.get());

            ListIter next = iter.next();
            if (next != null) pq.add(next);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(
            compute(Arrays.asList(1,10,8,3,5,20,18,10), 2)
        );
    }
    // 1 3 5 8 10 10 18 20
}
