package trees;

import com.sun.source.tree.Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class RightSibling {
    // For this problem, assume that each binary tree node has a extra field, call it level-next,
    // that holds a binary tree node (thisfield is distinct from the fieldsfor the left and right
    // children). The level-next field will be used to compute a map from nodes to their
    // right siblings. The input is assumed to be perfect binary tree. See Figure 10.6 for an
    // example.
    //
    // Write a program that takes a perfect binary tree, and sets each node'slevel-next field
    // to the node on its right, if one exists.

    // Example:
    //        1
    //   2    ->    3
    // 4 -> 5 ->  6 -> 7
    //
    // Solution:
    //
    // run two simultaneous recursions
    //   nope, this will not work
    //    as soon as we get to right, right comparison
    //    we would loose links
    // use breadths first traversal
    //
    // this way all nodes will be in correct orer immediately
    // Test:
    //     1
    // 2  -> 3
    // 4 5 6 7
    //

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode next;

        public TreeNode(int val, TreeNode l, TreeNode r) {
            this.val = val;
            this.left = l;
            this.right = r;
        }
    }

    public static TreeNode compute(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            List<TreeNode> currentLevel = new LinkedList<>();
            while (!q.isEmpty()) {
                currentLevel.add(q.remove());
            }
            TreeNode prev = null;
            for (TreeNode c : currentLevel) {
                if (prev != null) prev.next = c;
                prev = c;
                if (c.left != null) q.add(c.left);
                if (c.right !=null) q.add(c.right);
            }
        }
        return root;
    }

    public static void printNext(TreeNode node) {
        if (node == null) return;
        Integer next = node.next == null ? null : node.next.val;
        System.out.println("node(" + node.val + ") nextNode(" + next + ")");
        printNext(node.left);
        printNext(node.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
            1,
            new TreeNode(2,
                new TreeNode(4, null, null),
                new TreeNode(5, null, null)),
            new TreeNode(3,
                new TreeNode(6, null, null),
                new TreeNode(7, null, null)
            )
        );
        printNext(compute(root));
    }

}
