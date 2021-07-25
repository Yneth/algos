package linkedlists;

public class DeleteKthLastNode {
    // Without knowing the length of a linked list, it is not trivial to delete the /cth last
    // element in a singly linked list.
    // Given a singly linked list and an integer k, write a program to remove the /cth last
    // element from the list. Your algorithm cannot use more than a few words of storage,
    // regardless of the length of the list. In particular, you cannot assume that it is possible
    // to record the length of the list.
    //
    // Solution:
    // init two pointers
    // one start at k
    // one start at head
    // iterate both until kIter == null
    // Test:
    // list=1 2 3 4 5  k=2      target=4
    //
    //      i   k
    //        i   k
    //          i   k   => set node(3).next(node(5))

    public void deleteKth(ListNode list, int k) {
        ListNode kthIter = list;
        while (k-- > 0) kthIter = kthIter.next;

        ListNode iter = list;
        while (kthIter != null && kthIter.next != null) {
            kthIter = kthIter.next;
            iter = iter.next;
        }
        iter.next = iter.next.next;
    }

    static class ListNode {
        int v;
        ListNode next;
        public ListNode(int v, ListNode next) {
            this.v = v;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        DeleteKthLastNode sut = new DeleteKthLastNode();
        ListNode list = new ListNode(
            1,
            new ListNode(
                2,
                new ListNode(
                    3,
                    new ListNode(
                        4,
                        new ListNode(
                            5,
                            null
                        )
                    )
                )
            )
        );
        sut.deleteKth(list, 3);
        ListNode i = list;
        while (i != null) {
            System.out.print(i.v + " ");
            i = i.next;
        }
    }

}
