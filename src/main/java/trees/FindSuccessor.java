package trees;

import java.util.Arrays;

public class FindSuccessor {
    // Design an algorithm that computes the successor of a node in a binary tree. Assume
    // that each node storesits parent.
    //
    //          1
    //      2         3
    //        4    5     6
    //                  8
    //
    //    succ(2) = 4
    //    succ(4) = 1
    //    succ(1) = 5
    // 2 4 1 5 3 8 6
    // Solution:
    //   Given node n find its successor
    //
    //   findSuccessor(n)
    //     if n.right != null: return n.right
    //     else if n == n.parent.left return n.parent
    //     else if n == n.parent.right {
    //        iter = n
    //        while (iter.parent != null)
    //          iter = iter.parent
    //        if iter == iter.parent.left: return iter.parent
    //        if iter == iter.parent.right: return null
    //     }
    //    return null
    //
    //  succ(4)
    //   right == null
    //   4 is not left node of parent
    //   go up to 2
    //   2 is the left node of the root; return root
    //
    //   succ(2)
    //     right != null: return rigth
    //   succ(5)
    //     5 is the left node of paretn; return root
    //
    //   succ(6):
    //    no right
    //    is right child of parent
    //     go up to root
    //     return null as it is roots' right node
    //
    //      1
    //    2    3
    //  4  5  6 7

    static class TreeNode {
        int val;
        TreeNode parent, left, right;
        public TreeNode(int v, TreeNode l, TreeNode r) {
            this.val = v;
            this.left = l;
            this.right = r;
        }
    }

    public static Integer find(TreeNode n) {
        if (n.right != null) return n.right.val;
        else if (n.parent == null) return null;
        else if (n == n.parent.left) return n.parent.val;
        else if (n == n.parent.right) {
            TreeNode iter = n;
            while (iter.parent.parent != null) {
                iter = iter.parent;
            }
            if (iter == iter.parent.left) return iter.parent.val;
            else if (iter == iter.parent.right) return null;
        }
        return null;
    }

    public static void main(String[] args) {
        TreeNode l1000 = new TreeNode(4, null,null);
        TreeNode l0100 = new TreeNode(5, null,null);
        TreeNode l0010 = new TreeNode(6, null,null);
        TreeNode l0001 = new TreeNode(7, null,null);
        TreeNode l01 = new TreeNode(3, null, l0001);
        TreeNode l10 = new TreeNode(2, l1000, l0100);
        TreeNode root = new TreeNode(1, l10, l01);
        l01.parent = root;
        l10.parent = root;

        l1000.parent = l10;
        l0100.parent = l10;

        l0010.parent = l01;
        l0001.parent = l01;

        for (TreeNode n : Arrays.asList(
            root,
            l10,
            l01,
            l1000,
            l0100,
//            l0010,
            l0001
        )) {
            System.out.println("succ(" + n.val + ") == "  + FindSuccessor.find(n));
        }
    }
}

