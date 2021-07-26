package linkedlists;

public class IsPalindrome {

    // It is straightforward to check whether the sequence stored in an array is a palindrome.
    // However, if this sequence is stored as a singly linked list, the problem of detecting
    // palindromicity becomes more challenging.
    //
    // Questions:
    // am I allowed to make changes to linked list?
    // Solution:
    // init two pointers
    // init counter
    // fast/slow
    // iterate to while fast != null
    // if counter odd
    //   slow = slow.next
    // reverse(slow)
    // compare(reversed, head)
    //
    // Test:
    // 1 1 1 1 1 1
    // ^
    //   ^ ^
    //     ^   ^
    //       ^
    //       reverse
    // ^         ^
    //   ^     ^
    //     ^ ^
    // OK
    // 1 1 1
    // ^
    //   ^ ^
    //
    static class ListNode {
        int val;
        ListNode next;
        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
        public static ListNode of(int... vals) {
            ListNode head = new ListNode(vals[0], null);
            ListNode iter = head;
            for (int i = 1; i < vals.length; i++) {
                ListNode n = new ListNode(vals[i], null);
                iter.next = n;
                iter = n;
            }
            return head;
        }
    }

    public static ListNode reverse(ListNode head) {
        // * 1 2 3
        //   i h n
        // * h   i
        ListNode sentinelHead = new ListNode(0, null);
        ListNode iter = head;
        while (iter != null) {
            ListNode newHead = iter.next;
            if (newHead != null) {
                ListNode next = newHead.next;
                newHead.next = sentinelHead.next;
                sentinelHead.next = newHead;
                iter = next;
            } else {
                iter.next = sentinelHead.next;
                sentinelHead.next = iter;
                iter = null;
            }
        }
        return sentinelHead.next;
    }

    public boolean isPalindrome(ListNode head) {
        int count = 0;
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            count++;
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast == null) count++;
        if (count % 2 != 0) slow = slow.next;

        ListNode reversed = reverse(slow);
        ListNode iter = head;
        while (iter != null && reversed != null) {
            if (reversed.val != iter.val) return false;
            reversed = reversed.next;
            iter = iter.next;
        }
        return true;
    }

    public static void main(String[] args) {
        IsPalindrome sut = new IsPalindrome();
        System.out.println(sut.isPalindrome(ListNode.of(1, 2, 3, 4, 3, 2, 1)));
        System.out.println(sut.isPalindrome(ListNode.of(1, 2, 3, 3, 2, 1)));
        System.out.println(sut.isPalindrome(ListNode.of(1, 2)));
    }

}
