package sorting;

import java.util.*;

public class SortList {
    // Implement a routine which sorts lists efficiently. It should be a stable sort, i.e., the
    // relative positions of equal elements must remain unchanged.
    //
    // Solution:
    // - Implement insertion sort
    //
    // Example:
    // * 4 8 1 2 0
    //   ^ ^
    //     ^ ^
    //   1 8 4 2 0
    //     ^ ^
    //   1 4 8 2 0
    //     ^ ^
    //       ^ ^
    //   1 2 8 4 0
    //     ^ ^
    //       ^ ^
    //   1 2 4 8 0
    //       ^ ^
    //   1 2 4 8 0
    //         ^ ^
    //   0 2 4 8 1
    //         ^ ^
    //   0 1 4 8 2
    //   0 1 2 8 4
    //   0 1 2 4 8
    //
    // - MergeSort
    // 0 10 2 1 3 4
    // ^
    //    s f
    //      s   ^
    // -------+++++
    // --+++
    // - --++
    // 0 2 10
    //
    static class ListNode {
        int val;
        ListNode next;
        public ListNode(int v, ListNode n){
            this.val = v;
            this.next = n;
        }
        static ListNode of(int... arr) {
            ListNode head = new ListNode(arr[0], null);
            ListNode iter = head;
            for (int i = 1; i < arr.length; i++) {
                ListNode next = new ListNode(arr[i], null);
                iter.next = next;
                iter = next;
            }
            return head;
        }
    }

    private static ListNode mergeSorted(ListNode l0, ListNode l1) {
        ListNode resultHead = null;

        ListNode lIter = l0;
        ListNode rIter = l1;

        if (lIter.val > rIter.val) {
            resultHead = rIter;
            rIter = rIter.next;
        } else {
            resultHead = lIter;
            lIter = lIter.next;
        }

        ListNode cursor = resultHead;
        while (lIter != null && rIter != null) {
            int cmp = Integer.compare(lIter.val, rIter.val);
            if (cmp > 0) {
                cursor.next = rIter;
                rIter = rIter.next;
            } else {
                cursor.next = lIter;
                lIter = lIter.next;
            }
            cursor = cursor.next;
            cursor.next = null;
        }
        if (lIter != null) cursor.next = lIter;
        if (rIter != null) cursor.next = rIter;
        return resultHead;
    }

    public static ListNode compute(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode preslow = null, slow = head, fast = head;
        while (fast != null && fast.next != null) {
            preslow = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode low = head;
        ListNode mid = slow;
        preslow.next = null;
        return mergeSorted(compute(low), compute(mid));
    }

    private static void print(ListNode l) {
        while (l != null) {
            System.out.print(l.val + "  ");
            l = l.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        print(compute(ListNode.of(0, 10, 8, 20, 30, 50)));
        print(compute(ListNode.of(4,8,1,2,0)));

        print(mergeSorted(ListNode.of(0, 2, 4), ListNode.of(3, 5, 7)));
    }
}
