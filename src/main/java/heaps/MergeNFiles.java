package heaps;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MergeNFiles {

    // Write a program that takes asinput a set of sorted sequences and computesthe union
    // of these sequences as a sorted sequence. For example, if the input is (3,5,7), (0,6),
    // and (0,6, 28), then the output is(0,0,3,5,6,6,7, 28).
    //
    // Solution:
    // create data class for array iterator representation
    //  init min pq to always be populated by head of each list
    //
    //  at each step remove min
    //    add to resulting array
    //    iterate the removed index & add to pq
    // Test:
    // 0 10 20 40
    // 5 15 25 35
    // 3 17 22 36
    //
    //    0 5 3  -0 +10
    //   10 5 3  -3 +17
    //   10 5 17 -5 +15
    //   10 15 17 -10 +20
    //   20 15 17 -15 +25
    //   20 25 17 -17 +36
    // ...
    //
    static class ListIter implements Comparable<ListIter> {
        List<Integer> ref;
        int index;

        public ListIter(List<Integer> ref) {
            this.ref = ref;
            this.index = 0;
        }

        public ListIter next() {
            this.index++;
            if (this.index >= ref.size()) return null;
            return this;
        }

        public int get() {
            return this.ref.get(index);
        }

        @Override
        public int compareTo(@NotNull ListIter that) {
            return Integer.compare(this.get(), that.get());
        }
    }

    public static List<Integer> merge(List<List<Integer>> arrays) {
        List<Integer> result = new ArrayList<>();

        PriorityQueue<ListIter> pq = new PriorityQueue<>();
        for (List<Integer> arr : arrays) {
            if (!arr.isEmpty()) pq.add(new ListIter(arr));
        }
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
            merge(
                Arrays.asList(
                    Arrays.asList(0, 10, 20, 40),
                    Arrays.asList(5, 15, 25, 35),
                    Arrays.asList(3, 17, 22, 36)
                )
            )
        );
    }


}
