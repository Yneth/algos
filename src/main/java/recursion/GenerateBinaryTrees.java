package recursion;

import java.util.*;

public class GenerateBinaryTrees {
    //  Write a program which returns all distinct binary trees with a specified number of
    // nodes. For example, if the number of nodes is specified to be three, return the trees
    // in Figure 16.5.
    //
    // Example:
    // n = 3
    //      *      *         *
    //    *      *   *    *
    //  *                   *
    //  + symmetric         +symmetric
    // total = 5
    //
    // at each step we have two options
    // add left
    // add right
    //
    //  root #1
    //    addLeft(root, 1) #2
    //      addLeft(root, 0)
    //      addRight(root, 0)
    //    addRight(root, 1)
    //      addLeft(root, 0)
    //      addRigth(root, 0)
    //    this only covers 4 out of 5 cases
    //   we need one more
    //   add both

    static class TreeNode implements Cloneable {
        int val;
        TreeNode left, right;
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
        public TreeNode(int v) {
            this.val = v;
        }
        public Object clone() {
            TreeNode res = null;
            try {
                res = (TreeNode) super.clone();
            } catch (CloneNotSupportedException e) {
                res = this;
            }
            if (res.left != null)
                res.left = (TreeNode) res.left.clone();
            if (res.right != null)
                res.right = (TreeNode) res.right.clone();
            return res;
        }
        public void print(){
            Queue<TreeNode> q = new LinkedList<>();
            q.add(this);
            while (!q.isEmpty()) {
                List<TreeNode> level = new ArrayList<>();
                while (!q.isEmpty()) {
                    level.add(q.remove());
                }
                for (TreeNode n : level) {
                    String s = n == null ? "null" : n.val + "";
                    System.out.print(s + "    ");
                    if (n != null) {
                        q.add(n.left);
                        q.add(n.right);
                    }
                }
                System.out.println();
            }
        }
    }

    private static List<TreeNode> gen(int n, TreeNode root, TreeNode iter, List<TreeNode> res) {
        if (n < 0) return res;
        if (n == 0) {
            res.add((TreeNode) root.clone());
            return res;
        }
        iter.left = new TreeNode(n);
        iter.right = null;
        gen(n - 1, root, iter.left, res);

        iter.left = null;
        iter.right = new TreeNode(n);
        gen(n - 1, root, iter.right, res);

        iter.left = new TreeNode(n - 1);
        iter.right = new TreeNode(n);
        gen(n - 2, root, iter.left, res);

        iter.left = new TreeNode(n);
        iter.right = new TreeNode(n - 1);
        gen(n - 2, root, iter.right, res);

        return res;
    }

    public static List<TreeNode> gen(int n) {
        TreeNode root = new TreeNode(n);
        return gen(n - 1, root, root, new ArrayList<>());
    }

    public static List<TreeNode> generateAllTrees(int n) {
        List<TreeNode> result = new ArrayList<>();
        if (n == 0) {
            result.add(null);
        }
        for (int leftTreeNodes = 0; leftTreeNodes < n; leftTreeNodes++) {
            List<TreeNode> left = generateAllTrees(leftTreeNodes);

            int rightTreeNodes = n - leftTreeNodes - 1;
            List<TreeNode> right = generateAllTrees(rightTreeNodes);

            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    result.add(new TreeNode(n, l, r));
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        generateAllTrees(4).forEach(TreeNode::print);
    }
}
