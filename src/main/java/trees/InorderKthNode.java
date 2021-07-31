package trees;

public class InorderKthNode {
    // COMPUTE THE km NODE IN AN INORDER TRAVERSAL
    // Write a program that efficiently computes the kth node appearing in an inorder
    // traversal. Assume that each node stores the number of nodes in the subtree rooted at
    // that node.
    // Example:
    //         1
    //     2      3
    //   4   5   6   7
    // K = 3
    // output 4
    // if each node knows that it has the Kth node then we can reduce our
    // algorithm complexity to O(k)
    // lets consider a couple of cases:
    // #1:
    //   k is to the left of the root:
    //    k = 3
    //    start at 1
    //      we see that 2 has 3 nodes
    //      go to 2
    //        4 and 5 has 1 node each
    //        go to 5
    //    k is the root
    //     k = 4
    //     we see that left subtree has 3
    //     and right subtree has 3
    //     we can conclude that target is 4
    //   k is to the right of the root
    //     k = 6
    //     we see that left subtree has 3
    //     go 3
    //         k' = 2
    //       we see that left and right are both 1
    //       but right cannot be the one
    //       we pick 3
    // pseudocode:
    //    findInorderKth(node, k)
    //      if node == null: return null
    //      if k == 1: return node
    //      if node.left != null && Math.abs(node.left - k) == 1: return node.val
    //      if node.left != null && node.left.count >= k: findInorder(node.left, k)
    //      if node.right != null && node.right.count >=  k - node.left.count - 1: findInorder(node.right, k - node.left.count - 1)
    //      return null
    //
    // Test:
    //      1
    //   2     3
    // 4  5   6  7
    // k = 1
    //      3 >= 1
    //      go left
    //      1 >= 1
    //      go left
    //      return
    // k = 2
    //      3 >= 2
    //      go left
    //      1 >= 2
    //      2 - 1 == 1 return
    // k = 3
    //      3 >= 3 go left
    //      1 >= 3
    //      2 == 1
    //      1 >= 3 - 1 - 1
    //      1 == 1 return
    // k = 4
    //      3 >= 4
    //      4 - 3 == 1 return
    // k = 5
    //      3 >= 5
    //      5 - 3 == 1
    //      3 >= 5 - 3 - 1 == 3 >= 1

    static class TreeNode {
        int val;
        TreeNode left, right;
        int count;

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.count = (left == null ? 0 : left.count)
                + (right == null ? 0 : right.count)
                + 1;
        }
    }

    private static Integer doFindKthNode(TreeNode node, int k) {
        if (node == null) return null;
        if (node.left != null) {
            if (node.left.count >= k)
                return doFindKthNode(node.left, k);

            if (Math.abs(node.left.count - k) == 1)
                return node.val;
        }
        if (node.right != null) {
            int leftCount = node.left == null ? 0 : node.left.count;
            int newK = k - leftCount - 1;
            if (node.right.count >= newK)
                return doFindKthNode(node.right, newK);
        }
        return node.val;
    }

    public static Integer findKthNode(TreeNode root, int k) {
        return doFindKthNode(root, k);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
            1,
            new TreeNode(2,
                new TreeNode(4, null, null),
                new TreeNode(5, null, null)
            ),
            new TreeNode(3,
                new TreeNode(6, null, null),
                new TreeNode(7, null, null)
            )
        );
        for (int i = 1; i <= 7; i++) {
            System.out.println(
                InorderKthNode.findKthNode(root, i)
            );
        }
    }


}
