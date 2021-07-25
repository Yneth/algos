package linkedlists;

public class OverlappingListsNoCycles {
    // Given two singly linked lists there may be list nodes that are common to both. (This
    // may not be a bug—it may be desirable from the perspective of reducing memory
    // footprint, as in the flyweight pattern, or maintaining a canonical form.) For example,
    // the lists in Figure 8.6 overlap at Node 7.
    //
    // Write a program that takes two cycle-free singly linked lists, and determines if there
    // exists a node that is common to both lists.
    // Examples:
    // 12 -> 10 -> 8 --\
    //       11 -> 9 -> 7 -> 5 -> 6
    //
    //
    // |l1| = 6
    // |l2| = 5
    // Solution:
    //  if the task is to find if two linked lists
    //  are overlapping then we can just go to the
    //  tail of both lists and check if they are equal
    //  by pointer
    //
    //  if our task is to find the start of the overlap
    //  then we need more work
    //
    //  pick equally sized lists
    //  if |l1| > |l2|
    //    l1 drop |l1| - |l2| elements
    //  else
    //    l2 drop |l2| - |l1| elements
    //
    //   while l1i != l2i
    //     l1i++
    //     l2i++
    //  return l1i == l2i ? l1i : null
    //

    static class ListNode {
        int v;
        ListNode next;
        public ListNode(int v, ListNode next) {
            this.v = v;
            this.next = next;
        }
        public int length() {
            int count = 1;
            ListNode iter = this;
            while (iter != null) {
                count++;
                iter = iter.next;
            }
            return count;
        }
        public ListNode drop(int k) {
            ListNode iter = this;
            while (k-- > 0) {
                iter = iter.next;
            }
            return iter;
        }
    }

    public ListNode findOverlap(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) return null;
        if (l1 == l2) return l1;
        int l1len = l1.length(), l2len = l2.length();

        ListNode l1iter = l1;
        ListNode l2iter = l2;
        if (l1len > l2len) {
            l1iter = l1.drop(l1len - l2len);
        } else {
            l2iter = l2.drop(l2len - l1len);
        }
        while (l1iter != null && l2iter != null && l1iter != l2iter) {
            l1iter = l1iter.next;
            l2iter = l2iter.next;
        }
        return l1iter;
    }

    public static void main(String[] args) {
        OverlappingListsNoCycles sut = new OverlappingListsNoCycles();
        ListNode overlap = new ListNode(7, new ListNode(5, null));
        ListNode l1 = new ListNode(
            12,
            new ListNode(
                10,
                new ListNode(8, overlap)
            )
        );
        ListNode l2 = new ListNode(
            10, new ListNode(8, overlap)
        );

        System.out.println(sut.findOverlap(l1, l2).v);
        System.out.println(sut.findOverlap(l1, l1).v);
        System.out.println(sut.findOverlap(l2, l2).v);
        System.out.println(sut.findOverlap(null, l1));
        System.out.println(sut.findOverlap(l1, null));
        System.out.println(sut.findOverlap(new ListNode(1, null), new ListNode(2, null)));
    }
}

