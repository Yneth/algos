package linkedlists;


public class ReverseSublist {

    static class ListNode {
        int v;
        ListNode next;

        public ListNode(int v, ListNode next) {
            this.v = v;
            this.next = next;
        }

        public static ListNode of(int... vals) {
            ListNode head = new ListNode(vals[0], null);
            ListNode iter = head;
            for (int i = 1; i < vals.length; i++) {
                iter.next = new ListNode(vals[i], null);
                iter = iter.next;
            }
            return head;
        }
    }

    // Write a program which takes a singly linked list L and two integers s and as
    // arguments, and reverses the order of the nodes from the sth node to nth node,
    // inclusive. The numbering begins at 1, i.e., the head node is the first node. Do not
    // allocate additional nodes.

    // Solution:
    // init subListHead at head
    // init size of the sublist
    //
    // while s > 1
    //   subListHead = subListHead.next
    //
    // 1 2 3 4 5 6 7
    //   h i t     ^
    //     4 3 5 6 7
    //   h t i
    //     5 4 3 6 7
    //     6 4 3 5 7
    //     7 6 4 3 5
    // iter = subListHead
    // while size-- > 0
    //   temp = iter.next
    //   iter.next = temp.next
    //   temp.next = subListHead.next
    //   subListHead.next = temp
    // Time: O(e)
    // Space: O(1)
    // SolutionTime: 33:45

    public ListNode reverseSubList(ListNode l, int s, int e) {
        if (s == e) return l;

        ListNode sentinel = new ListNode(0, l);
        ListNode subListHead = sentinel;
        int k = 1;
        while (k++ < s) {
            subListHead = subListHead.next;
        }

        int len = e - s;
        ListNode iter = subListHead.next;
        while (len-- > 0 && iter != null) {
            ListNode temp = iter.next;
            iter.next = temp.next;
            temp.next = subListHead.next;
            subListHead.next = temp;
        }

        return sentinel.next;
    }

    public static void main(String[] args) {
        ReverseSublist rs = new ReverseSublist();
        ListNode res = rs.reverseSubList(
            ListNode.of(1, 2, 3, 4, 5, 6, 7),
            2,
            7
        );
        while (res != null) {
            System.out.print(res.v + " ");
            res = res.next;
        }
        System.out.println();
    }

}
