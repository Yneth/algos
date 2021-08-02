package trees;

import java.util.Arrays;
import java.util.List;

public class FromPreorder {
    // Design an algorithm for reconstructing a binary tree from a preorder traversal visit
    // sequence that uses null to mark empty children.
    //
    //     1
    //  2      3
    //4   5       7
    //   8
    //
    //   1 2 4 null null 5 8 null null null 3 null 7 null null
    //   ^ 0
    //     ^ 1
    //       ^ 2
    //          ^ 3
    //              ^ 4
    //       ^ 4
    //                   ^ 5
    // we always know root and its left node & right if left is null
    //
    // r(1).left = 2
    //    r(2).left = 4
    //        r(5).left = 8
    //    r(2).right = n(5)
    // r(1).right = 3
    //    r(3).left = null
    //    r(3).right = 7
    // pseudocode
    //    from_preorder(preorder, i)
    //      if (i > len(preorder)) return;
    //      root = preorder[i]
    //      if root == null: return null, i

    //      left, left_i = from_preorder(preorder, i + 1)
    //      root.left = left

    //      right, right_i = from_preorder(preorder, left_i + 1)
    //      root.right = right
    //
    //      return root, right_i + 1

    static class TreeNode {
        int val;
        TreeNode left, right;
    }

    static class Result {
        TreeNode node;
        int endIndex;

        public Result(TreeNode n, int i) {
            this.node = n;
            this.endIndex = i;
        }
    }

    private static Result doFrom(List<Integer> vals, int i) {
        if (i >= vals.size()) return null; // throw exception
        Integer val = vals.get(i);
        if (val == null) return null;

        TreeNode root = new TreeNode();
        root.val = val;

        Result left = doFrom(vals, i + 1);
        root.left = left != null ? left.node : null;

        int leftIndex = left != null ? left.endIndex : (i + 1);
        Result right = doFrom(vals, leftIndex + 1);
        root.right = right != null ? right.node : null;

        int rightEndIndex = right != null ? right.endIndex : leftIndex + 1;
        return new Result(root, rightEndIndex);
    }

    public static TreeNode from(List<Integer> vals) {
        Result root = doFrom(vals, 0);
        if (root == null) return null;
        return root.node;
    }

    private static void printTree(TreeNode root) {
        if (root == null) return;
        System.out.println("node(" + root.val + ")");
        if (root.left != null)
            System.out.println("left = node(" + root.left.val + ")");
        if (root.right != null)
            System.out.println("right = node(" + root.right.val + ")");
        printTree(root.left);
        printTree(root.right);
    }

    public static void main(String[] args) {
        printTree(
            from(
                Arrays.asList(1, 2, 4, null, null, 5, 8, null, null, null, 3, null, 7, null, null)
            )
        );

    }
}
