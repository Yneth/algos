package heaps;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class HeapLargestKElements {
    // A heap contains limited information about the ordering of elements,so unlike a sorted
    // array or a balanced BST, naive algorithms for computing the k largest elements have
    // a time complexity that depends linearly on the number of elements in the collection.
    // Given a max-heap, represented as an array A,design an algorithm that computes the k
    // largest elements stored in the max-heap. You cannot modify the heap. For example, if
    // the heap is the one shown in Figure11.1(a) on Page175, then the array representation
    // is(561,314,401, 28,156,359, 271,11,3), the four largest elements are 561,314, 401, and
    // 359.
    //
    // Example:
    //  300,200,201,10,142,170,190
    //  300 200 201 190
    // Solution:
    //   use heap to store child nodes of the currently reviewed node
    //   add root
    //    for i = 0 i < k; i++
    //       last = heap.remove()
    //       result.add(last.val)
    //       heap.add(left)
    //       head.add(right)
    // Test:
    // k = 4
    //  +300
    //   200 201
    //    +201
    //     200,170,190
    //      +200
    //       170,190,10,142
    //      +190

    static class HeapEntry implements Comparable<HeapEntry> {
        int i;
        int val;
        public HeapEntry(int i, int v) {
            this.i = i;
            this.val = v;
        }

        @Override
        public int compareTo(@NotNull HeapEntry o) {
            return Integer.compare(o.val, this.val);
        }
    }

    public static List<Integer> compute(int[] heap, int k) {
        PriorityQueue<HeapEntry> pq = new PriorityQueue<>();
        List<Integer> result = new ArrayList<>();
        pq.add(new HeapEntry(0, heap[0]));
        for (int i = 0; i < k && !pq.isEmpty(); i++) {
            HeapEntry e = pq.remove();
            result.add(e.val);

            int leftI = 2 * e.i + 1;
            if (leftI < heap.length) pq.add(new HeapEntry(leftI, heap[leftI]));
            int rightI = 2 * e.i + 2;
            if (rightI < heap.length) pq.add(new HeapEntry(rightI, heap[rightI]));
        }
        return result;
    }

    public static void main(String[] args) {
        for (int i = 1; i < 6; i++) {
            System.out.println(i + ", " + compute(new int[] {300,200,201,10,142,170,190}, i));
        }
    }

}
