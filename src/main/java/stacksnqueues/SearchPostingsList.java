package stacksnqueues;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class SearchPostingsList {
    // A postings list is a singly linked list with an additional "jump" field at each node.
    // The jump field points to any other node. Figure 9.3 illustrates a postings list with
    // four nodes.
    // One way to enumerate the nodes in a postingslist is to iteratively follow the next
    // field. Another is to always first follow the jump field if it leads to a node that has
    // not been explored previously, and then search from the next node. Call the order in
    // which these nodes are traversed the jump-first order.
    // Write recursive and iterative routines that take a postingslist, and compute the jumpfirst order.
    // Assume each node has an integer-valued field that holds the order, and is
    // initialized to -1.
    //
    // Example:
    // 1 -> 2 -> 3 -> 4       1 -> 3, 3 -> 2,  2-> 4, 4 -> 4
    //
    // Solution:
    //   recursive
    //     iter(node, i)
    //       if (node == null) return
    //       if (node.val != -1) return
    //       node.val = i
    //       iter(node.nextJump, i + 1)
    //
    //     iter(head, 0)
    //
    //   iterative:
    //      iter(head)
    //        stack = []
    //        push(head)
    //        i = 0
    //        while not empty(stack)
    //           curr = stack.pop()
    //           if (curr.val != -1) return
    //           curr.val = i
    //           stack.push(curr.nextJump)
    //
    // Questions:
    //    what to return?
    // Test:
    //    1 -> 2 -> 3 -> 4       1 -> 3, 3 -> 2,  2-> 4, 4 -> 4
    //    0    2    1    3
    //
    //

    static class PostingListNode {
        int val = -1;
        PostingListNode next;
        PostingListNode jump;
    }

    private int doJumpOrder(PostingListNode node, int order) {
        if (node != null && node.val == -1) {
            node.val = order++;
            order = doJumpOrder(node.jump, order);
            order = doJumpOrder(node.next, order);
        }
        return order;
    }

    public void jumpOrderRecursive(PostingListNode head) {
        doJumpOrder(head, 0);
    }

    public void jumpOrderIter(PostingListNode head) {
        Deque<PostingListNode> stack = new LinkedList<>();
        stack.push(head);
        int order = 0;
        while (!stack.isEmpty()) {
            PostingListNode curr = stack.pop();
            if (curr == null || curr.val != -1) continue;
            curr.val = order++;
            stack.push(curr.next);
            stack.push(curr.jump);
        }
    }

    public static void test(Consumer<PostingListNode> run) {
        PostingListNode head =  new PostingListNode();
        head.next = new PostingListNode();
        head.next.next = new PostingListNode();
        head.next.next.next = new PostingListNode();
        head.next.next.next.next = new PostingListNode();
        head.jump = head.next.next;
        head.next.next.jump = head.next;
        head.next.jump = head.next.next.next;

        run.accept(head);
        PostingListNode i = head;
        while (i != null) {
            System.out.println(i.val);
            i = i.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SearchPostingsList sut = new SearchPostingsList();
        test(list -> sut.jumpOrderRecursive(list));
        test(list -> sut.jumpOrderIter(list));
    }
}
