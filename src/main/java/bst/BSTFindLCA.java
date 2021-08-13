package bst;

import java.util.Arrays;
import java.util.List;

public class BSTFindLCA {
    //  Since a BST is a specialized binary tree, the notion of lowest common ancestor, as
    // expressed in Problem 10.4 on Page 157, holdsfor BSTs too.
    // In general, computing the LCA of two nodesin a BST is no easier than computing
    // theLCA in a binary tree,since structurally a binary tree can be viewed as a BST where
    // all the keys are equal. However, when the keys are distinct, it is possible to improve
    // on the LCA algorithmsfor binary trees.
    // Design an algorithm that takes asinput a BST and two nodes, and returns the LCA
    // of the two nodes. For example, for the BST in Figure 15.1 on Page 255, and nodes C
    // and G, your algorithm should return B. Assume all keys are distinct. Nodes do not
    // have references to their parents.
    // Example:
    // >        10
    //   5          15
    // 1   7           20
    // [1,20]
    //   1 < 10: 1 should be searched at the left subtree
    //           15 in the right
    //   > 5    [left, null]
    //    1 < 5: search left
    //   1 == 1 found return current node
    // same for 20
    //    and at the root we have
    //    left = 1
    //    rigth = 20
    //    lca = 10
    //
    // Solution:
    //   bst_lca(node, left, right):
    //      if node == null: return null
    //      if node == left || node == right: return node
    //      res_left  = if left == null: null
    //                  else  {
    //                     if left.val > node.val: bst_lca(node.right, left, if (node.val < right.val) null : right)
    //                     else bst_lca(node.left, left, if (node.val > right.val) null : right)
    //                  }
    //      res_right = .....
    //

    static class TreeNode {
        int val;
        TreeNode left, right;

        public TreeNode(int v, TreeNode l, TreeNode r) {
            this.val = v;
            this.left = l;
            this.right = r;
        }
    }

    private static String toStr(TreeNode n) {
        return n == null ? "null" : n.val + "";
    }

    static class LCAResult {
        TreeNode left, right;

        public static LCAResult left(TreeNode n) {
            LCAResult val = new LCAResult();
            val.left = n;
            return val;
        }

        public static LCAResult right(TreeNode n) {
            LCAResult val = new LCAResult();
            val.right = n;
            return val;
        }

        public boolean terminate() {
            return left != null && right != null;
        }
    }

    private static TreeNode doFind(TreeNode node, TreeNode l, TreeNode r) {
        if (node == null) return null;
        if (node == l || node == r) return node;

        boolean leftInLeftSub = r != null && l.val < node.val;
        boolean rightInLeftSub = r != null && r.val < node.val;
        if (leftInLeftSub && rightInLeftSub) {
            return doFind(node.left, l, r);
        } else if (!leftInLeftSub && !rightInLeftSub) {
            return doFind(node.right, l, r);
        } else {
            return node;
        }
    }

    public static TreeNode findLCA(TreeNode root, TreeNode l, TreeNode r) {
        if (l.val > r.val) {
            TreeNode tmp = l;
            l = r;
            r = tmp;
        }
        return doFind(root, l, r);
    }

    public static void main(String[] args) {
        TreeNode l0001 = new TreeNode(20, null, null);
        TreeNode l01 = new TreeNode(15, null, l0001);

        TreeNode l1000 = new TreeNode(1, null, null);
        TreeNode l0100 = new TreeNode(7, null, null);
        TreeNode l10 = new TreeNode(5, l1000, l0100);
        TreeNode root = new TreeNode(10, l10, l01);
        for (List<TreeNode> p : Arrays.asList(
            Arrays.asList(l01, l10),
            Arrays.asList(root, l10),
            Arrays.asList(l10, root),
            Arrays.asList(l0100, l10),
            Arrays.asList(l0100, l1000),
            Arrays.asList(l0001, l01),
            Arrays.asList(l0100, l0100),
            Arrays.asList(l0001, l0100)
        )) {
            TreeNode res = findLCA(root, p.get(0), p.get(1));
            String str = res == null ? "null" : res.val + "";
            System.out.println(
                "(" + p.get(0).val + "," + p.get(1).val + ")  = " + str
            );
        }
    }
}
