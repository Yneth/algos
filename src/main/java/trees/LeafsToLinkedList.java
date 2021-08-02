package trees;

import java.util.LinkedList;
import java.util.List;

public class LeafsToLinkedList {

    // Given a binary tree, compute a linked list from the leaves of the binary tree. The
    // leavesshould appear in left-to-right order. For example, when applied to the binary
    // tree in Figure 10.1 on Page 150, your function should return (D,£, H,M,N,P).

    // Solution:
    //   pick all nodes that have no child
    //
    // Test:
    //        1
    //     2     3
    //   4   5     8
    //      6     9
    // 4 6 9
    //
    //  inorder traversal
    //   1 -> 2 -> 4 add
    //          -> 5 -> 6 add
    //        3 -> 8 -> 9 add

    static class TreeNode {
        int val;
        TreeNode left, right;
        public TreeNode(int v, TreeNode l, TreeNode r) {
            this.val = v;
            this.left = l;
            this.right = r;
        }
    }

    public static List<Integer> doCompute(TreeNode node, List<Integer> results) {
        if (node == null) return results;
        if (node.left == null && node.right == null) {
            results.add(node.val);
        } else {
            doCompute(node.left, results);
            doCompute(node.right, results);
        }
        return results;
    }

    public static List<Integer> compute(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        return doCompute(root, result);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,
            new TreeNode(2, null, null),
            new TreeNode(3,
                new TreeNode(7, null, null),
                new TreeNode(8, null, null)
            )
        );
        System.out.println(compute(root));
    }

}
