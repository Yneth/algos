package trees;

public class ComputeBinaryNumbers {
    // Design an algorithm to compute the sum of the binary numbers represented by the
    // root-to-leaf paths.
    //
    // Example:
    //         1
    //      0        1
    //    0  1      0      0
    //  0 0  1 0   0 0    1 1
    //
    // 1000, 1000, 1011, 1010, 1100, 1100, 1101, 1101
    // 8   + 8   + 11  + 10  + 12  + 12  + 13  + 13 = 87
    //     16    27    37    49    61    74    87
    //
    //        1 << 0 = 2
    //        1 << 1  + 0 << 0
    //        1 << 2  + 0 << 1 + 0
    //        1 << 3  + 0 << 2 + 0 << 1 + 0
    //        1 << 0 = 1
    //        1 << 1 + 0 = 2
    //        2 * 2 + 1 = 5
    //        5 * 2 + 1 = 11
    //
    // Solution:
    //  recursive
    //  compute(n, acc)
    //     if n == null return acc
    //     left = compute(n.left, acc * 2 + n.val)
    //     right = compute(n.right, acc * 2 + n.val)
    //     return left + right

    static class TreeNode {
        int v;
        TreeNode l, r;

        public TreeNode(int v, TreeNode l, TreeNode r) {
            this.v = v;
            this.l = l;
            this.r = r;
        }
    }

    private static int doCompute(TreeNode node, int acc) {
        if (node == null) return acc;
        int newValue = acc * 2 + node.v;
        int left = doCompute(node.l, newValue);
        int right = doCompute(node.r, newValue);
        return left + right;
    }

    public static int compute(TreeNode root) {
        return doCompute(root, 0) / 2;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
            1,
            new TreeNode(0,
                new TreeNode(0,
                    new TreeNode(0, null, null),
                    new TreeNode(0, null, null)),
                new TreeNode(1,
                    new TreeNode(1, null, null),
                    new TreeNode(0, null, null))),
            new TreeNode(1,
                new TreeNode(0,
                    new TreeNode(0, null, null),
                    new TreeNode(0, null, null)),
                new TreeNode(0,
                    new TreeNode(1, null, null),
                    new TreeNode(1, null, null))
            )
        );
        System.out.println(ComputeBinaryNumbers.compute(root));
    }
}
