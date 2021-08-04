package heaps;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class OnlineDataMedian {
    // You want to compute the running median of a sequence of numbers. The sequence is
    // presented to you in a streaming fashion—you cannot back up to read an earlier value,
    // and you need to output the median after reading in each new element. For example,
    // if the input is1,0,3, 5, 2,0,1 the output is 1,0.5,1,2,2,1.5,1.
    //
    // Design an algorithm for computing the running median of a sequence.

    // Example:
    // 1 5 10 11
    // 1 3 5  7.5
    //
    // Solution:
    // Keep two heaps
    //    max for min elements
    //    min for max elements
    //
    // add()
    //   add to minPq
    //   if minPq.size() > maxPq.size()
    //     maxPq.add(minPq.remove())
    //
    // avg()
    //   if minPq.size() > maxPq.size()
    //     minPq.peek()
    //     (minPq.peek() + maxPq.peek()) / 2

    private PriorityQueue<Integer> min = new PriorityQueue<>();
    private PriorityQueue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder());

    public void add(int v) {
        if (min.isEmpty()) min.add(v);
        else if (v > min.peek()) min.add(v);
        else max.add(v);

        if (min.size() > max.size())
            max.add(min.remove());
    }

    public double median() {
        if (max.size() > min.size())
            return max.peek();
        return (min.peek() + max.peek()) / 2.0;
    }

    public static void main(String[] args) {
        OnlineDataMedian sut = new OnlineDataMedian();
        for (int v : Arrays.asList(1, 5, 10, 11)) {
            sut.add(v);
            System.out.println(sut.median());
        }
    }

}
