package heaps;

import java.util.*;

public class SortAlmostSorted {
    // Often data is almost-sorted—for example, a server receivestimestamped stock quotes
    // and earlier quotesmay arriveslightly afterlater quotesbecauseof differencesin server
    // loads and network routes. In thisproblem we address efficient waystosortsuch data.
    //
    // Write a program which takes as input a very long sequence of numbers and prints
    // the numbersin sorted order. Each number is at most k away from its correctly sorted
    // position. (Such an array issometimes referred to as being For example, no
    // number in the sequence (3,-1,2,6,4,5,8} is more than 2 away from its final sorted
    // position.
    //
    // Example:
    //    k = 2
    //    arr = [1 0 3 4 10 5 6 11]

    // Solution:
    // init pq with k first elements
    //  pq [ 1 0 ]
    // init writeIndex = 0
    //  start at i = k
    //  at each step
    //  add element to pq
    //  remove
    //  write

    // Test:
    //  pq:1 0 3 //   0
    //  pq:1 3 4  //  0  1
    //  pq:3 4 10 // 0 1 3
    //  pq: 4 10 5 // 0 1 3 4
    //  pq: 10 5 6 // 0 1 3 4 5
    //  pq: 10 6 11 // 0 1 3 4 5 6
    // Then consume whats' left
    //  0 1 3 4 5 6 10 11


    public static List<Integer> sort(Iterator<Integer> data, int k) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            if (data.hasNext()) pq.add(data.next());
        }
        while (data.hasNext()) {
            pq.add(data.next());

            int val = pq.remove();
            result.add(val);
        }
        while (!pq.isEmpty()) {
            result.add(pq.remove());
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(
            sort(Arrays.asList(1, 0, 3, 4, 10, 5, 6, 11).iterator(), 2)
        );

        System.out.println(
            sort(Arrays.asList(1, 0, 3, 4).iterator(), 5)
        );
    }
}

