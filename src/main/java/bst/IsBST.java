package bst;

public class IsBST {
    //  Write a program that takes as input a binary tree and checks if the tree satisfies the
    // BST property.
    //  Hint: Is it correct to check for each node that its key is greater than or equal to the key at itsleft
    // child and lessthan or equal to the key at its right child?
    //
    // solution:
    // bst has a following properties
    // left nodes are less than current
    // right nodes are higher then the current
    // so
    // example:
    //       1
    // -10     10
    //    3   2
    // not a BST
    //
    //                 1
    //  [,1]                  [1,]
    //     [-10,1]       [1,10]
    //
    // pseudocode:
    //   isBST(node, l, h):
    //       if node == null: return true
    //       return (l == null || node.v >= l)
    //              && (h == null || node.v <= h)
    //              && isBST(node.left, l, h == null ? node.v : Math.min(node.v, h))
    //              && isBST(node.right, l == null ? node.v : Math.max(node.v, l), h)
    // test:
    //       1/
    //   -10 < 1 && isBST(3, -10, 1)
    //
    //        1
    //  -1          3
    // -2  0
    //         1
    // isBST(-1, null, 1)
    // -----(-2, null, -1)
    // -----(0,-1,1)
    // -----(3,1,null)
    static class TreeNode {
        int val;
        TreeNode left, right;
        public TreeNode(int val, TreeNode l, TreeNode r) {
            this.val = val;
            this.left = l;
            this.right = r;
        }
    }

    private static boolean isBST(TreeNode n, Integer l, Integer r) {
        if (n == null) return true;
        int v = n.val;
        return (l == null || v >= l) && (r == null || v <= r)
            && isBST(n.left, l, r == null ? v : Math.min(v, r))
            && isBST(n.right, l == null ? v : Math.max(v, l), r);
    }

    public static boolean isBST(TreeNode root) {
        return isBST(root, null, null);
    }

    public static void main(String[] args) {
        TreeNode l0010 = new TreeNode(0, null, null);
        TreeNode l01 = new TreeNode(10, l0010, null);
//        TreeNode l0100 = new TreeNode(2, null, null);
//        TreeNode l10 = new TreeNode(-10, null, l0100);
        TreeNode l10 = new TreeNode(-10, null, null);
        TreeNode root = new TreeNode(1, l10, l01);
        System.out.println(isBST(root));
    }
}
