package bst;

import java.util.*;

public class ReconstructBSTFromTraversalData {
    //  Suppose you are given the sequence in which keys are visited in an inorder traversal
    // of a BST, and all keys are distinct. Can you reconstruct the BST from the sequence?
    // If so, write a program to do so. Solve the same problem for preorder and postorder
    // traversal sequences.
    //       10
    //   5       15
    // 1   7        21
    //             20
    //
    // inorder:  1 5 7 10 15 20 21
    //
    // We can reconstruct with root at N possible positions
    //  how to pick root for inorder traversal?
    //  middle, no. why?
    //  if we pick middle it will be balanced
    // inorder traversal of BST == sorted sequence
    // then picking a middle would end up in a balanced BSt
    // if it is OK, then I proceed
    //
    // Test:
    // - 10    then we try to add middles of the left/right subtrees
    // -  5,15
    // -1,7 21
    // -    20
    //
    // Preorder:
    // 10, 5, 1, 7, 15, 21, 20
    // ^ root
    //     ^ left subtree
    //              ^ right subtree
    //     ^ root
    //         ^ left
    //            ^ right
    // pseudocode:
    //     fromPreorder(list, start, end)
    //       if (start >= end) return null
    //       mid = start
    //       while (list[start] > list[mid])
    //          mid++
    //       return new TreeNode(
    //          list[start],
    //          fromPreorder(list, start + 1, mid)
    //          fromPreorder(list, mid, end)
    //       );

    static class TreeNode {
        int val;
        TreeNode left, right;
        public TreeNode(int v, TreeNode l, TreeNode r) {
            this.val = v;
            this.left = l;
            this.right = r;
        }

    }

    private static TreeNode fromRec(List<Integer> preorder, int s, int e) {
        if (s >= e) return null;
        int m = s;
        while (m < e && preorder.get(s) >= preorder.get(m)) m++;
        return new TreeNode(
            preorder.get(s),
            fromRec(preorder, s + 1, m),
            fromRec(preorder, m, e)
        );
    }

    public static TreeNode from(List<Integer> preorder) {
        return fromRec(preorder, 0, preorder.size());
    }

    private static TreeNode printNode(TreeNode n) {
        String res = n == null ? "null" : n.val + " ";
        System.out.print(res + " ");
        return n;
    }

    private static void print(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            List<TreeNode> nodes = new ArrayList<>();
            while (!q.isEmpty()) {
                nodes.add(printNode(q.remove()));
            }
            System.out.println();
            for (TreeNode n : nodes) {
                if (n == null) continue;
                q.add(n.left);
                q.add(n.right);
            }
        }
    }

    public static void main(String[] args) {
        print(from(Arrays.asList(10, 5, 1, 7, 15, 21, 20)));
        print(from(Arrays.asList(10, 15, 20)));
        print(from(Arrays.asList(20, 15, 10)));
    }
}
