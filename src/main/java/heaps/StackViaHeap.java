package heaps;

import java.util.PriorityQueue;

public class StackViaHeap {
    // We discussed the notion of reduction when describing problem-solving patterns.
    // Usually, reductions are used to solve a more complex problem using a solution to a
    // simpler problem as a subroutine.
    //
    // Occasionally it makes sense to go the other way—for example, if we need the
    // functionality of a heap, we can use a BST library, which is more commonly available,
    // with modest performance penalties with respect, for example, to an array-based
    // implementation of a heap.
    //
    // How would you implement a stack API using a heap?
    //
    // Stack is LIFO
    // we can assign to each inserted element a timestamp
    // and use MaxHeap based on timestamp
    //
    // Test:
    // 100 at 12:00
    // 200 at 12:01
    // 300 at 13:01
    // pop() -> 300
    // pop() -> 200
    //
    static class Entry {
        int val;
        int index;

        public Entry(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }

    private PriorityQueue<Entry> maxPq = new PriorityQueue<>(
        (e0, e1) -> Long.compare(e1.index, e0.index)
    );

    private int counter = 0;

    public void push(int val) {
        maxPq.add(new Entry(val, counter++));
    }

    public Integer pop() {
        Entry e = maxPq.remove();
        return e.val;
    }

    public Integer peek() {
        Entry e = maxPq.peek();
        return e != null ? e.val : null;
    }

    public boolean isEmpty() {
        return maxPq.isEmpty();
    }

    public static void main(String[] args) {
        StackViaHeap sut = new StackViaHeap();
        sut.push(100);
        sut.push(200);
        sut.push(0);

        while (!sut.isEmpty()) {
            System.out.println(sut.pop());
        }
    }
}
