package bst;

 import java.util.*;

public class KLargest {
    //  A BST is a sorted data structure, which suggests that it should be possible to find the
    // k largest keys easily.
    // Write a program that takes asinput a BST and an integer k, and returns the k largest
    // elements in the BST in decreasing order. For example, if the input is the BST in
    // Figure 15.1 on Page 255 and k = 3, your program should return (53,47,43).
    //
    // I can use right leaning inorder traversal
    // with a list as accumulator
    // as soon as list of the needed size, return
    //
    // Example:
    //
    //       10
    //   5        15
    // 1        13    21
    //
    // k = 3
    // 10 -> 15 -> 21
    //             add 21
    //       +15
    //           -> 13
    //              +13
    //
    // k = 5
    // +10
    //   ->  5
    //       +5
    // Algo:
    //  kLargest(node, k)
    //   if node == null: return []
    //   right = kLargest(node.right, k)
    //   if len(right) < k: right.add(node)
    //   if len(right) < k:
    //     return right.addAll(kLargest(node.left, k))
    //   return right

    static class TreeNode {
        int val;
        TreeNode left, right;
        public TreeNode(int v, TreeNode l, TreeNode r) {
            this.val = v;
            this.left = l;
            this.right = r;
        }
    }

    private static List<Integer> doGet(TreeNode node, int k) {
        if (node == null) return new ArrayList<>();
        List<Integer> right = doGet(node.right, k);
        if (right.size() < k) right.add(node.val);
        if (right.size() < k) right.addAll(doGet(node.left, k));
        return right;
    }

    public static List<Integer> get(TreeNode root, int k) {
        return doGet(root, k);
    }

    public static void main(String[] args) {
        TreeNode l0010 = new TreeNode(13, null, null);
        TreeNode l0001 = new TreeNode(23, null, null);
        TreeNode l01 = new TreeNode(15, l0010, l0001);
        TreeNode l1000 = new TreeNode(1, null, null);
        TreeNode l10 = new TreeNode(5, l1000, null);
        TreeNode root = new TreeNode(10, l10, l01);
        for (int i = 0; i < 8; i++) {
            System.out.println(i + "   " + get(root, i));
        }
    }
}
