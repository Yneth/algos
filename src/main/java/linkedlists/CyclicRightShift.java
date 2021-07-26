package linkedlists;

public class CyclicRightShift {
    // This problem is concerned with performing a cyclic right shift on a list.
    // Write a program that takes as input a singly linked list and a non negative integer k,
    // and returns the list cyclically shifted to the right by k. See Figure 8.9 for an example
    // of a cyclic rightshift.
    //
    // Solution:
    // init two pointers
    // one at 0              pointer for node(k)
    // second at kth         pointer for last(node)
    // iterate both until second.next != null
    //
    // last_node.next = start
    // kth_node.next = null
    //
    // Test:
    // 1 -> 2 -> 3 -> 4 -> 5       k=2
    // ^         ^
    //      ^         ^
    //           ^         ^
    //
    //  3 -> null
    //  5 -> 1

    static class ListNode {
        int val;
        ListNode next;
        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
        public static ListNode of(int... arr) {
            ListNode head = new ListNode(arr[0], null);
            ListNode iter = head;
            for (int i = 1; i < arr.length; i++) {
                ListNode n = new ListNode(arr[i], null);
                iter.next = n;
                iter = n;
            }
            return head;
        }
        public static void print(ListNode head) {
            ListNode iter = head;
            while (iter != null) {
                System.out.print(iter.val + " ");
                iter = iter.next;
            }
            System.out.println();
        }
    }

    public ListNode cyclicRightShift(ListNode head, int k) {
        if (head == null) return null;
        if (k == 0) return head;

        ListNode tail = head;
        while (k-- > 0 && tail != null) tail = tail.next;
        if (tail == null) return head;

        ListNode kth = head;
        while (tail.next != null) {
            kth = kth.next;
            tail = tail.next;
        }

        ListNode newHead = kth.next;
        tail.next = head;
        kth.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        CyclicRightShift sut = new CyclicRightShift();
        for (int i = 0; i < 6; i++) {
            ListNode.print(sut.cyclicRightShift(ListNode.of(1, 2, 3, 4, 5), i));
        }
    }

}
