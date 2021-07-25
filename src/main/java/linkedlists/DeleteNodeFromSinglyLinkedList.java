package linkedlists;

public class DeleteNodeFromSinglyLinkedList {
    // Given a node in a singly linked list, deleting it in 0(1) time appears impossible because
    // its predecessor's next field has to be updated. Surprisingly, it can be done with one
    // small caveat—the node to delete cannot be the last one in the list and it is easy to copy
    // the value part of a node.
    // Write a program which deletes a node in a singly linked list. The input node is
    // guaranteed not to be the tail node.
    // Solution:
    // given list and node_n
    // copy values from node_n+1 to node_n
    //
    // pseudocode:
    // node_n.val = node_n.next.val
    // node_n.next = node_n.next.next
    //
    // Test:
    // 1 -> 2 -> 3 -> 4 -> nil
    //      ^
    // 1 -> 3 -> 3 -> 4 -> nil node.val = 3
    // 1 -> 3 -> 4 -> nil node.next = node.next.next

    static class ListNode {
        int v;
        ListNode next;
        public ListNode(int v, ListNode next) {
            this.v = v;
            this.next = next;
        }
    }

    public void deleteNode(ListNode toDelete) {
        toDelete.v = toDelete.next.v;
        toDelete.next = toDelete.next.next;
    }

    public static void print(ListNode list) {
        ListNode iter = list;
        while (iter != null) {
            System.out.print(iter.v + " ");
            iter = iter.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DeleteNodeFromSinglyLinkedList sut = new DeleteNodeFromSinglyLinkedList();

        ListNode toDelete = new ListNode(2, new ListNode(3, null));
        ListNode list = new ListNode(1, toDelete);
        sut.deleteNode(toDelete);

        print(list);
    }

}
