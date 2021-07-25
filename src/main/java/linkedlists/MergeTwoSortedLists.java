package linkedlists;

class ListNode {
    int v;
    ListNode next;

    public static ListNode of(int... vals) {
        ListNode head = new ListNode();
        head.v = vals[0];
        ListNode cur = head;
        for (int i = 1; i < vals.length; i++) {
            ListNode n = new ListNode();
            n.v = vals[i];
            cur.next = n;
            cur = n;
        }
        return head;
    }
}

public class MergeTwoSortedLists {

    // Consider two singly linked lists in which each node holds a number. Assume the lists
    // are sorted, i.e., numbers in the lists appear in ascending order within each list. The
    // merge of the two lists is a list consisting of the nodes of the two lists in which numbers
    // appear in ascending order.
    // Write a program that takes two lists, assumed to be sorted, and returns their merge.
    // The only field your program can change in a node is its next field.


    // Solution
    // if one list is null return the other
    // init head with the smallest value of the two heads
    // init cursor with a head value
    // we need to keep reference to a head in order to be able to return it as a result
    // while (either of lists is not empty)
    //    if one of the list heads is less
    //       append to the cursor
    //       move to the next element
    // if one of the lists is not empty
    //     add it as the tail to the cursor
    // Test:
    // 1 5 7       2 3
    // head 1, cursor 1
    // #1: cursor 2     5 7 | 3
    // #2: cursor 3     5 7 | _
    // add 5 7
    // return
    // Time: O(n)
    // Space: O(1) we are reusing the same nodes
    // TotalSolutionTime: 17:02

    public ListNode merge(ListNode left, ListNode right) {
        if (left == null) return right;
        if (right == null) return left;

        ListNode head;
        ListNode l = left;
        ListNode r = right;

        if (left.v < right.v) {
            head = l;
            l = l.next;
        } else {
            head = r;
            r = r.next;
        }

        ListNode cursor = head;
        while (l != null && r != null) {
            if (l.v < r.v) {
                cursor.next = l;
                cursor = l;
                l = l.next;
            } else {
                cursor.next = r;
                cursor = r;
                r = r.next;
            }
        }
        if (l != null) cursor.next = l;
        if (r != null) cursor.next = r;

        return head;
    }

    public static void runTest(MergeTwoSortedLists sut, ListNode l, ListNode r) {
        ListNode res = sut.merge(l, r);
        while (res != null) {
            System.out.print(res.v + "  ");
            res = res.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MergeTwoSortedLists mtwsl = new MergeTwoSortedLists();
        runTest(mtwsl, ListNode.of(1, 5, 7), ListNode.of(2, 3));
        runTest(mtwsl, ListNode.of(2, 3), ListNode.of(1, 5, 7));
        runTest(mtwsl, null, ListNode.of(1, 5, 7));
        runTest(mtwsl, ListNode.of(1, 5, 7), null);

        runTest(mtwsl, ListNode.of(-1, 100, 100000), ListNode.of(10, 99, 1011));
    }

}
