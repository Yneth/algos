package linkedlists;

public class FindCycle {
    // Although a linked list is supposed to be a sequence of nodes ending in null, it is
    // possible to create a cycle in a linked list by making the next field of an element
    // reference to one of the earlier nodes.
    // Write a program that takes the head of a singly linked list and returns null if there
    // does not exist a cycle, and the node at the start of the cycle, if a cycle is present. (You
    // do not know the length of the list in advance.)
    //
    // Solution:
    // Two iterators, fast that moves 2 steps
    //                slow            1 step
    // Test:
    // 1  -> 2 -> 3 -> 4 -> 1
    // sf
    // ---
    //       s    f
    // ---
    //            s          f
    // ---
    //       f         s
    // ---
    //                 f     s
    // sf
    // SolutionTime: 13:52
    // Time: O(C) + O(F) + O(C)
    // Space: O(1)


    static class ListNode {
        int v;
        ListNode next;

        public ListNode(int v, ListNode next) {
            this.v = v;
            this.next = next;
        }

        @Override
        public String toString() {
            if (next != null)
                return v + " -> " + next.v + " -> *";
            return v + " -> null";
        }
    }

    public ListNode findCycle(ListNode l) {
        ListNode f = l;
        ListNode s = l;
        boolean hasCycle = false;
        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
            if (s == f) {
                hasCycle = true;
                break;
            }
        }
        if (hasCycle) {
            // This solution is wrong by the way
            // we did not find the start of the cycle.
            // the point of convergence may not be a
            // start of the cycle.
            // consider the following case:
            // |L| = 2*n - 1
            // ... xn  xn1  xn2 xn3  -> xn
            // and fast pointer starts at xn1
            //          ^
            //                   ^
            //          ^
            // as you can see it will never get to
            // xn or xn2. this way in order to find
            // the start of the cycle we need to do
            // some additional work.
            // lets say we found a cycle.
            // memorize the current node
            // get the cycle length
            // 1 2 3 4 5 6 7 -> 4
            // cycle_len 4
            // start adv_iter at cycle_len (4)
            // start iter at head (1)
            // move both by 1
            // ^       ^
            //   ^       ^
            //     ^       ^
            //       x
            //
            // 1 2 0 0 0 3 4 ->3
            // cycle_len = 2
            // ^   ^
            //   ^   ^
            //     ^   ^
            //       ^   ^
            //         ^   ^
            //           x
            ListNode iter = l;
            while (iter.next != s) iter = iter.next;
            return iter;
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode noCycle = new ListNode(0,
            new ListNode(1,
                new ListNode(2, null)
            )
        );
        ListNode cycle = new ListNode(1, null);
        cycle.next = new ListNode(2,
            new ListNode(3, cycle)
        );
        FindCycle sut = new FindCycle();
        System.out.println(sut.findCycle(noCycle));
        System.out.println(sut.findCycle(cycle));
    }

}
