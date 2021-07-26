package linkedlists;

public class Pivot {
    // Implement a function which takes as input a singly linked list and an integer k and
    // performs a pivot of the list with respect to k. The relative ordering of nodes that
    // appear before k, and after k, must remain unchanged; the same must hold for nodes
    // holding keys equal to k.

    // Example:
    // list=9 5 1 6 7 2 3 0   k=1,  list[k] = 5
    //
    //      1 2 3 0 5 8 6 7
    //
    // Question: K is an index?

    // Solution:
    // init two sentinel nodes
    // left, right
    //
    // find Kth element
    //
    // iterate over the list
    //   if iter.val > kth.val
    //      rightIter.next = iter
    //      rightIter = iter
    //   else
    //      left...
    //
    // leftIter.next = kth
    // kth.next = right
    // return left
    //
    // Test:
    // list=9 5 1 6 7 2 3 0   k=1,  list[k] = 5
    //
    //      left  1 2 3 0
    //      right 9 6 7

    static class ListNode {
        int val;
        ListNode next;
        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
        public static ListNode of(int... vals) {
            ListNode head = new ListNode(vals[0], null);
            ListNode iter = head;
            for (int i = 1; i < vals.length; i++) {
                ListNode n = new ListNode(vals[i], null);
                iter.next = n;
                iter = n;
            }
            return head;
        }
    }

    public ListNode pivot(ListNode head, int k) {
        ListNode kth = head;
        while (k-- > 0) kth = kth.next;

        ListNode left = new ListNode(0, null);
        ListNode right = new ListNode(0, null);

        ListNode iter = head, leftIter = left, rightIter = right;
        while (iter != null) {
            ListNode next = iter.next;
            iter.next = null;
            if (iter.val > kth.val) {
                rightIter.next = iter;
                rightIter = rightIter.next;
            } else if (iter != kth) {
                leftIter.next = iter;
                leftIter = leftIter.next;
            }
            iter = next;
        }
        if (left.next == null) {
            kth.next = right.next;
            return kth;
        }
        leftIter.next = kth;
        kth.next = right.next;
        return left.next;
    }

    public static void main(String[] args) {
        Pivot sut = new Pivot();
        ListNode iter = sut.pivot(ListNode.of(9,5,1,6,7,2,3,0), 1);
        while (iter != null) {
            System.out.print(iter.val + " ");
            iter = iter.next;
        }
        System.out.println();
    }
}
