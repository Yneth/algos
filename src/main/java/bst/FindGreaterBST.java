package bst;

public class FindGreaterBST {
    //  Write a program that takes as input a BST and a value, and returns the first key
    // that would appear in an inorder traversal which is greater than the input value. For
    // example, when applied to the BST in Figure 15.1 on Page 255 you should return 29
    // for input 23.
    //
    // Example:
    //       10
    //   5        15
    // 1   7    13    20
    //
    // 1 -> 5
    // 7 -> 10
    // 15 -> 20
    // 13 -> 15
    // 10 -> 15
    // first greater is the same as next in the inorder traversal
    //
    // case analysis:
    // 15 -> go right
    // 7 -> go to the parent
    //
    // algo:
    // if right != null: return right
    // if node == parent.left: return parent
    // if node == parent.right:
    //   while (parent.parent != null && parent.parent.right != null)
    //     parent = parent.parent
    //
    // pseudocoe:
    //   greater(node, val):
    //     iter = node
    //     res = null
    //     while (iter != null)
    //       if (iter.val > val)
    //          if (res == null) res = iter.val
    //          else Math.min(res, iter.val)
    //          iter = iter.left
    //       else
    //          iter = iter.right
    //     return res
    //
    // Test:
    //       10
    //   5        15
    // 1   7    13    20
    //
    // 15:
    // - 10 < 15 -> go right
    // 15 < 15 -> go right
    // 20
    //
    // 7:
    //  10 > 7 -> go left
    // 5 < 7 go right
    // 7 < 7 return 10
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

    public static TreeNode find(TreeNode root, int val) {
        TreeNode iter = root;
        TreeNode result = null;
        while (iter != null) {
            if (iter.val > val) {
                if (result == null || result.val > iter.val) {
                    result = iter;
                }
                iter = iter.left;
            } else {
                iter = iter.right;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode l0010 = new TreeNode(13, null, null);
        TreeNode l0001 = new TreeNode(20, null, null);
        TreeNode l0100 = new TreeNode(7, null, null);
        TreeNode l1000 = new TreeNode(1, null, null);
        TreeNode l01 = new TreeNode(15, l0010, l0001);
        TreeNode l10 = new TreeNode(5, l1000, l0100);
        TreeNode root = new TreeNode(10, l10, l01);
        for (int i = 0; i <= 21; i++) {
            TreeNode res = find(root, i);
            String resStr = res == null ? "null" : res.val + "";
            System.out.println(i + ",    "  + resStr);
        }
    }

}
