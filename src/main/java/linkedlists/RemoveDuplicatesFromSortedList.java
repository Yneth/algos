package linkedlists;

public class RemoveDuplicatesFromSortedList {
    // Write a program that takes as input a singly linked list of integers in sorted order, and
    // removes duplicates from it. The list should be sorted.

    // Solution:
    // iterate over the list
    // if current is equal to next
    //   move forward until not equal
    // link current to non equal one
    // repeat
    //
    // Test:
    // 1 1 1 2 2 3 4 5 5
    // ^                  1 == 1
    // run nested loop
    //   ^
    //     ^
    //       ^            new element
    // 1 -> 2
    //       ^            2 == 2
    // run nested loop
    //         >
    //           >
    // 2 -> 3
    //           ^
    //             ^
    //               ^
    // pseudocode:
    // def rm_dups(l):
    //    iter = l
    //    while iter != null:
    //       if iter.val == iter.next.val:
    //           next_iter = iter.next
    //           while next_iter == null || next_iter.val == iter.val:
    //              next_iter = next_iter.next
    //           iter.next = next_iter
    //       else:
    //           iter = iter.next

    public static class ListNode {
        int val;
        ListNode next;
        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public void removeDuplicates(ListNode head) {
        if (head == null) return;

        ListNode iter = head;
        while (iter != null && iter.next != null) {
            if (iter.val == iter.next.val) {
                ListNode next = iter.next;
                while (next != null && next.val == iter.val) next = next.next;
                iter.next = next;
                iter = next;
            } else {
                iter = iter.next;
            }
        }
    }

    public static void main(String[] args) {
        RemoveDuplicatesFromSortedList sut = new RemoveDuplicatesFromSortedList();
//        ListNode list = new ListNode(1,
//            new ListNode(1,
//                new ListNode(1,
//                    new ListNode(2,
//                        new ListNode(2,
//                            new ListNode(3,
//                                new ListNode(3, null)))))));
//        ListNode list = new ListNode(1,null);
        ListNode list = new ListNode(1, new ListNode(1, new ListNode(1, null)));
        sut.removeDuplicates(list);

        ListNode iter = list;
        while (iter != null) {
            System.out.print(iter.val + " ");
            iter = iter.next;
        }
    }
}
