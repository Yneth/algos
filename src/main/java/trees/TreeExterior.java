package trees;

import java.util.ArrayList;
import java.util.List;

public class TreeExterior {
    // The exterior of a binary tree is the following sequence of nodes: the nodes from the
    // root to the leftmost leaf, followed by the leavesin left-to-right order, followed by the
    // nodesfrom the rightmost leaf to the root. (By leftmost (rightmost) leaf, we mean the
    // leaf that appears first (last) in an inorder traversal.) For example, the exterior of the
    // binary tree in Figure 10.1 on Page 150 is{A,B,C,D,E,H,M,N,P,O,I).
    //
    // Example:
    //             1
    //        2        3
    //     4     5       8
    //          6       7
    //
    //  1 2 4 6 7 8 3
    //
    //            1 add
    //        2 add
    //    4 add
    // no child leave
    //    6 add
    //        complete
    //        3 to be added later
    //            8 to be added later
    //                 7 add
    //
    //
    //
    // Solution:
    //  two separate algos for left and right subtrees
    //      left(node, boundary?)
    //         if node == null return
    //         if boundary? || (node.left == null && node.right == null)
    //              add(node)
    //         left(node.left, boundary?)
    //         left(node.right, false)
    //
    //       add1 b?=true
    //          add2 b?=true
    //            add4 b?=true
    //            visit5
    //            add6 l==null&&
    //
    //      right(node, boundary?)
    //         if node == null return
    //         right(node.left, false)
    //         right(node.right, boundary?)
    //         if boundary? || (node.left == && node.right == null)
    //            add(node)
    //

    static class TreeNode {
        int val;
        TreeNode left, right;

        public TreeNode(int val, TreeNode l, TreeNode r) {
            this.val = val;
            this.left = l;
            this.right = r;
        }
    }

    public static boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

    public static List<TreeNode> computeLeft(TreeNode node, boolean boundary) {
        if (node == null) return new ArrayList<>();
        List<TreeNode> result = new ArrayList<>();
        if (boundary || isLeaf(node)) result.add(node);
        System.out.println("node(" + node.val +")  " + boundary + "    " + isLeaf(node));
        result.addAll(computeLeft(node.left, boundary));
        result.addAll(computeLeft(node.right, false));
        return result;
    }

    public static List<TreeNode> computeRight(TreeNode node, boolean boundary) {
        if (node == null) return new ArrayList<>();
        List<TreeNode> result = new ArrayList<>();
        result.addAll(computeRight(node.left, false));
        result.addAll(computeRight(node.right, boundary));
        if (boundary || isLeaf(node)) result.add(node);
        return result;
    }

    public static List<TreeNode> compute(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        result.add(root);
        result.addAll(computeLeft(root.left, true));
        result.addAll(computeRight(root.right, true));
        return result;
    }

    //      1
    //  2      3
    // 4 5       7
    //    6     8
    public static void main(String[] args) {
        TreeNode root = new TreeNode(
            1,
            new TreeNode(2,
                new TreeNode(4, null, null),
                new TreeNode(5, null, new TreeNode(6, null, null))
            ),
            new TreeNode(3,
                null,
                new TreeNode(7, new TreeNode(8, null, null), null)
            )
        );
        for (TreeNode n : compute(root)) {
            System.out.println(n.val);
        }
    }

}
