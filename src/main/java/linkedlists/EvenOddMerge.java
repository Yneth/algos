package linkedlists;

public class EvenOddMerge {
    // Consider a singly linked list whose nodes are numbered starting at 0.
    // Define the even odd merge of the list to be the list consisting of the
    // even-numbered nodes followed by the odd-numbered nodes.
    //
    // Example:
    // l0 -> l1 -> l2 -> l3 -> l4 -> l5
    // l0 -> l2 -> l4 -> l1 -> l3 -> l5
    //
    // Solution:
    // init two sentinel heads
    // one for odd numbers
    // one for even numbers
    //
    // iterate over input linkedlist
    // using a counter
    // if counter odd
    //   add to odd iter
    //   add to even iter
    //
    // evenIter.next = oddHead

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

        public static void printLink(ListNode head) {
            ListNode iter = head;
            while (iter != null) {
                System.out.print(iter + " ");
                iter = iter.next;
            }
            System.out.println();
        }
    }

    public ListNode evenOddMerge(ListNode head) {
        ListNode evenHead = new ListNode(0, null);
        ListNode oddHead = new ListNode(0, null);

        ListNode evenIter = evenHead;
        ListNode oddIter = oddHead;
        ListNode iter = head;
        int i = 0;
        while (iter != null) {
            ListNode next = iter.next;
            if (i % 2 == 0) {
                evenIter.next = iter;
                evenIter = iter;
            } else {
                oddIter.next = iter;
                oddIter = iter;
            }
            iter.next = null;
            i++;
            iter = next;
        }
        if (evenHead.next != null) {
            evenIter.next = oddHead.next;
            return evenHead.next;
        }
        return oddHead.next;
    }

    public static void main(String[] args) {
        EvenOddMerge sut = new EvenOddMerge();
        ListNode of = ListNode.of(0, 1, 2, 3, 4, 5, 6);
        ListNode.printLink(of);
        ListNode.printLink(sut.evenOddMerge(of));

        ListNode.print(sut.evenOddMerge(ListNode.of(0, 1, 2, 3, 4, 5)));
        ListNode.print(sut.evenOddMerge(ListNode.of(0)));
        ListNode.print(sut.evenOddMerge(ListNode.of(0, 1)));
        ListNode.print(sut.evenOddMerge(null));
    }
}
