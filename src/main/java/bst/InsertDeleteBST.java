package bst;

import java.util.*;

public class InsertDeleteBST {
    //  A BST is a dynamic data structure—in particular, if implemented carefully key inser¬
    // tion and deletion can be made very fast.
    //  Design efficient functions for inserting and removing keys in a BST. Assume that all
    // elements in the BST are unique, and that your insertion method must preserve this
    // property.
    //
    // Example:
    //       10
    //   5        15
    // 2    8
    //1 3  7 9
    //
    // Delete 15
    //     just set node to null
    // Delete 8
    //    replace with node 9
    // Delete 5
    //  replace with 8
    //   move left subtree to the left of the 8
    //
    // Insert
    // simply follow the rules of the tree
    // find first null node and set

    static class TreeNode {
        int val;
        TreeNode left, right;

        public TreeNode(int v, TreeNode l, TreeNode r) {
            this.val = v;
            this.left = l;
            this.right = r;
        }
    }

    TreeNode root = null;


    public void insert(int val) {
        TreeNode newNode = new TreeNode(val, null, null);

        if (root == null) {
            root = newNode;
            return;
        }

        TreeNode prev;
        TreeNode iter = root;
        while (iter != null) {
            int cmp = Integer.compare(val, iter.val);
            prev = iter;

            if (cmp == 0) break;
            else if (cmp > 0) iter = iter.right;
            else if (cmp < 0) iter = iter.left;

            if (iter == null && prev != null) {
                if (cmp > 0) prev.right = newNode;
                else prev.left = newNode;
            }
        }
    }

    private void add(TreeNode to, TreeNode toAdd) {
        TreeNode prev = to;
        TreeNode iter = to;
        while (iter != null) {
            prev = iter;
            int cmp = Integer.compare(toAdd.val, iter.val);
            if (cmp > 0) iter = iter.right;
            else if (cmp < 0) iter = iter.left;
            if (iter == null) {
                if (cmp > 0) prev.right = toAdd;
                else if (cmp < 0) prev.left = toAdd;
            }
        }
    }

    public void delete(int val) {
        if (root == null) return;

        TreeNode prev = null;
        TreeNode iter = root;
        while (iter != null) {
            int cmp = Integer.compare(val, iter.val);
            if (cmp != 0) prev = iter;

            if (cmp < 0) iter = iter.left;
            else if (cmp > 0) iter = iter.right;
            else { break; }
        }
        if (iter == null) return;

        boolean isRoot = prev == null;
        boolean isLeft = prev != null && prev.left == iter;
        if (iter.left == null && iter.right == null) {
            if (isRoot) root = null;
            else if (isLeft) prev.left = null;
            else prev.right = null;
        } else if (iter.left != null && iter.right == null) {
            if (isRoot) root = iter.left;
            else if (isLeft) prev.left = iter.left;
            else prev.right = iter.left;
        } else if (iter.left == null && iter.right != null) {
            if (isRoot) root = iter.right;
            else if (isLeft) prev.left = iter.right;
            else prev.right = iter.right;
        } else /* if (iter.left != null && iter.right != null) */ {
            if (isRoot) root = iter.right;
            else if (isLeft) prev.left = iter.right;
            else prev.right = iter.right;

            add(iter.right, iter.left);
        }
    }

    public void print() {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            List<TreeNode> level = new ArrayList<>();
            while (!q.isEmpty()) {
                level.add(q.remove());
            }
            for (TreeNode n : level) {
                System.out.print(n == null ? "null" : n.val + "");
                System.out.print("   ");
                if (n == null) continue;
                q.add(n.left);
                q.add(n.right);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        InsertDeleteBST tree = new InsertDeleteBST();
        for (int v : Arrays.asList(10, 5, 3, 7, 15, 11, 16)) {
            tree.insert(v);
            tree.print();
            System.out.println("======================");
        }

        System.out.println(">>>>>>>>>>>>><<<<<<<<<<<<<<");
        for (int i = 20; i >= 0; i--) {
            tree.delete(i);
            tree.print();
            System.out.println("======================");
        }
    }
}
