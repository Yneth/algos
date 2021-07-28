package queues;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class MaxQueue {
    // Implement a queue with enqueue, dequeue, and max operations. The max operation
    // returns the maximum element currently stored in the queue.
    //
    // Example:
    // 1 3 4 5 1        max 5
    // 3 4  5 1     max 5
    // 4 5 1    max 5
    // 5 1       max 5
    // 1         max 1
    //
    //
    // 5 4 3 2 1      max 5
    // 4 3 2 1        max 4
    // 3 2 1          max 3
    // 2 1            max 2
    // 2 1 100        max 100
    // 1 100          max 100
    //
    // Solution:
    // init two queues
    // one for queue itself
    // one for max candidates
    //
    // enqueue(val)
    //    while (not empty cands && cands.peek() < val)
    //      cands.remove()
    //    queue.add(val)
    //
    // dequeue()
    //   val = queue.remove()
    //   while (not empty cands && cands.peek() < val)
    //      cands.remove()
    //   if (cands.peek() == val) cands.remove()
    //   return val
    //
    //  max()
    //    if (cands.isEmpty() throw
    //    return cands.peek()
    //
    // Example:
    // 2              max:2
    // 2 1            max:2,1
    // 2 1 5          max:5
    // 2 1 5 3 4      max:5,4
    // 1 5 3 4        max:5,4
    // 1 5 3 4        max:5,4
    // 5 3 4          max:5,4
    // 3 4            max:4

    private Queue<Integer> queue = new LinkedList<>();
    private Deque<Integer> candidates = new LinkedList<>();

    public void enqueue(int val) {
        while (!candidates.isEmpty() && candidates.getLast() < val) {
            candidates.removeLast();
        }
        candidates.addLast(val);
        queue.add(val);
    }

    public Integer dequeue() {
        int val = queue.remove();
        while (!candidates.isEmpty() && candidates.getFirst() < val) {
            candidates.removeFirst();
        }
        if (Integer.valueOf(val).equals(candidates.getFirst()))
            candidates.removeFirst();
        return val;
    }

    public Integer max() {
        return candidates.peek();
    }

    public static void main(String[] args) {
        MaxQueue sut = new MaxQueue();
        sut.enqueue(2);
        System.out.println(sut.max());
        sut.enqueue(1);
        System.out.println(sut.max());
        sut.enqueue(5);
        System.out.println(sut.max());
        sut.enqueue(3);
        System.out.println(sut.max());
        sut.enqueue(4);
        System.out.println(sut.max());
        sut.dequeue();
        System.out.println(sut.max());
        sut.dequeue();
        System.out.println(sut.max());
        sut.dequeue();
        System.out.println(sut.max());
        sut.dequeue();
        System.out.println(sut.max());
    }

}
