package trees;

import java.util.ArrayList;
import java.util.List;

public class ConstantInorderIterative2 {
    // The direct implementation of an inorder traversal using recursion has 0(h) space
    // complexity, where h is the height of the tree. Recursion can be removed with an
    // explicit stack, but the space complexity remains0(h).
    // Write a non recursive program for computing the inorder traversal sequence for a
    // binary tree. Assume nodes have parent fields.

    // Time: O(n + h)
    //
    // Solution:
    // init prev, curr nodes
    //    if curr.parent == prev
    //     if hit leaf
    //         go right or to parent
    //    if curr.left == prev
    //       add curr
    //       move to right or parent
    //    otherwise
    //      move to parent

    static class TreeNode {
        int val;
        TreeNode parent, left, right;
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static List<Integer> compute(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        TreeNode prev = null, curr = root;
        while (curr != null) {
            TreeNode next;
            if (curr.parent == prev) {
                if (curr.left != null) next = curr.left;
                else {
                    results.add(curr.val);
                    next = curr.right != null ? curr.right : curr.parent;
                }
            } else if (curr.left == prev) {
                results.add(curr.val);
                next = curr.right != null ? curr.right : curr.parent;
            } else {
                next = curr.parent;
            }
            prev = curr;
            curr = next;
        }
        return results;
    }

    public static void main(String[] args) {
        TreeNode a1000 = new TreeNode(4, null, null);
        TreeNode a0100 = new TreeNode(5, null, null);
        TreeNode a0010 = new TreeNode(6, null, null);
        TreeNode a0001 = new TreeNode(7, null, null);
        TreeNode a01 = new TreeNode(3, a0010, a0001);
        TreeNode a10 = new TreeNode(2, a1000, a0100);
        TreeNode root = new TreeNode(1, a10, a01);
        a10.parent = root;
        a01.parent = root;
        a0001.parent = a01;
        a0010.parent = a01;
        a1000.parent = a10;
        a0100.parent = a10;

        System.out.println(ConstantInorderIterative2.compute(root));
    }

}
