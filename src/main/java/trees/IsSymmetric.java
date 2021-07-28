package trees;

public class IsSymmetric {

    // A binary tree issymmetric if you can draw a vertical line through the root and then
    // the left subtree is the mirror image of the right subtree. The concept of a symmetric
    // binary tree isillustrated in Figure 10.3 on the facing page.
    // Write a program that checks whether a binary tree issymmetric.

    // Example:
    //    1
    // 2      2
    //3 4   4  3
    //
    // 1
    //2 3
    //
    //Solution:
    // make recursion for nodes at the same time
    //   for the left and right ones
    //  and check if their values are equal
    //  then use recursion for left.left == right.right
    //                         left.right === right.left
    //pseudocode:
    // check(left, right)
    //   if left != right: return false
    //   return check(left.left, right.right)
    //          && check(left.right, right.left)
    //test:
    //  1
    // 2 3
    //  2 != 3
    //
    //     2 == 2
    // 3 == 3    4 == 4


    static class BinaryTreeNode {
        int v;
        BinaryTreeNode left, right;
        public BinaryTreeNode(int v, BinaryTreeNode l, BinaryTreeNode r) {
            this.v = v;
            this.left = l;
            this.right = r;
        }
    }

    private static boolean doCheck(BinaryTreeNode l, BinaryTreeNode r) {
        if (r == null && l == null) return true;
        if (r != null && l == null) return false;
        if (r == null && l != null) return false;
        if (r.v != l.v) return false;
        return doCheck(l.left, r.right) && doCheck(l.right, r.left);
    }

    public static boolean check(BinaryTreeNode root) {
        if (root == null) return true;
        return doCheck(root.left, root.right);
    }

    public static void main(String[] args) {
        BinaryTreeNode test0 =  new BinaryTreeNode(
            1,
            new BinaryTreeNode(2, new BinaryTreeNode(3, null,null), new BinaryTreeNode(4, null, null)),
            new BinaryTreeNode(2, new BinaryTreeNode(4, null,null), new BinaryTreeNode(3, null, null))
        );
        System.out.println(check(test0));
    }

}
