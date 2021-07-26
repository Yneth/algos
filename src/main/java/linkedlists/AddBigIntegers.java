package linkedlists;

public class AddBigIntegers {
    // Write a program which takes two singly linked lists of digits, and returns the list
    // corresponding to the sum of the integers they represent. The least significant digit
    // comes first.
    //
    // Example:
    //  321  999
    // 1 2 3
    // 9 9 9
    // 0 2 3 1
    //
    // 1320
    // what should I return?
    //  number or list?
    //  if list, which order?
    //
    // Solution:
    // init two iterators
    //    sum = l + r + incNext ? 1 : 0
    //    incNext = sum >= 10
    //    sum = sum % 10
    // if incNext
    //   add 1
    // 5 min

    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode add(ListNode l, ListNode r) {
        ListNode res = new ListNode(0, null);
        ListNode resIter = res;
        boolean incNext = false;
        while (l != null || r != null) {
            int lv = l == null ? 0 : l.val;
            int rv = r == null ? 0 : r.val;
            int sum = lv + rv + (incNext ? 1 : 0);
            incNext = sum >= 10;
            sum = sum % 10;

            resIter.next = new ListNode(sum, null);
            resIter = resIter.next;

            if (l != null) l = l.next;
            if (r != null) r = r.next;
        }
        if (incNext) {
            resIter.next = new ListNode(1, null);
        }
        return res.next;
    }

    public static void main(String[] args) {
        AddBigIntegers sut = new AddBigIntegers();
        ListNode res = sut.add(
            new ListNode(1, null),
            new ListNode(9, new ListNode(9, new ListNode(9, null)))
        );
        while (res != null) {
            System.out.print(res.val + "  ");
            res = res.next;
        }
        System.out.println();
    }

}

