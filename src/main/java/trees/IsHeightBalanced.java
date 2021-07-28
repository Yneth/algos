package trees;

public class IsHeightBalanced {
    // A binary tree issaid to be height-balanced if for each node in the tree, the difference
    // in the height of its left and right subtrees is at most one. A perfect binary tree is
    // height-balanced, asis a complete binary tree. A height-balanced binary tree does not
    // have to be perfect or complete—see Figure 10.2 on the facing page for an example.
    // Write a program that takes asinput the root of a binary tree and checks whether the
    // tree is height-balanced.
    //
    // Solution:
    // recurse using inorder traversal
    // height as accumulator incremented at each step
    // as soon as we hit leaf return height
    // return Math.abs(leftHeight - rightHeight) <= 1;
    //
    // Pseudocode:
    //   balanced(node, height)
    //      if node == null: return height, true
    //      ls, lh = balanced(node.left, height + 1)
    //      rs, rh = balanced(node.right, height + 1)
    //      return height, ls && rs && abs(lh - rh) <= 1
    //
    // Test:
    //          1             <- 0
    //     2  3     4 5
    //   6 7  8 9
    //
    //   6 -> 3
    //   7 -> 3
    //   2 -> 3-3  true
    //   3 -> 3 - 3 true
    //

    static class HeightAndStatus {
        int height;
        boolean status;
        public HeightAndStatus(int height, boolean status) {
            this.height = height;
            this.status = status;
        }
    }

    static class BinaryTreeNode {
        int val;
        BinaryTreeNode left, right;
        public BinaryTreeNode(int v, BinaryTreeNode l, BinaryTreeNode r) {
            this.val = v;
            this.left = l;
            this.right = r;
        }
    }

    private HeightAndStatus doCheck(BinaryTreeNode n, int h) {
        if (n == null) return new HeightAndStatus(h, true);
        HeightAndStatus l = doCheck(n.left, h + 1);
        HeightAndStatus r = doCheck(n.right, h + 1);
        System.out.println("node = " + n.val + ", left = " + l.height + ", right = " + r.height);
        boolean newStatus = l.status && r.status && Math.abs(l.height - r.height) <= 1;
        return new HeightAndStatus(Math.max(l.height, r.height), newStatus);
    }

    public boolean check(BinaryTreeNode root) {
        return doCheck(root, 0).status;
    }

    public static void main(String[] args) {
        IsHeightBalanced sut = new IsHeightBalanced();
        //    1
        //  2    3
        // 4
        System.out.println(sut.check(new BinaryTreeNode(
            1,
            null,
            null
        )));
    }

}
