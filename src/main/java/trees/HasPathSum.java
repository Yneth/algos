package trees;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HasPathSum {

    // You are given a binary tree where each node is labeled with an integer. The path
    // weight of a node in such a tree is the sum of the integers on the unique path from the
    // root to that node. For the example shown in Figure 10.1 on Page 150, the path weight
    // of E is 591.
    // Write a program which takes asinput an integer and a binary tree with integer node
    // weights, and checksif there exists a leaf whose path weight equals the given integer.
    //
    // Example
    //         1
    //   10  3    7  13
    // 1         3
    //
    // 11
    //  1->10
    //  1->7->3
    // 12
    //  1->10->1
    //
    // 4
    //     1->3
    // 14
    //  1->13
    //
    // Solution:
    //  What do I need to return?
    //  just boolean if tree contains such path?
    //  or all nodes that give the following sum
    //
    //
    //   recursive
    //     accumulator would be a sum from the root to the current
    //     second accumulator - list of target nodes
    //     result - void
    //
    // Test:
    //     1              target = 11
    // 10    10
    //       1 2
    //
    //  1->10 11 add
    //    no reason to go deeper, as it may result in values higher
    //
    //  1-> 10

    static class TreeNode {
        int v;
        TreeNode l, r;
        public TreeNode(int v, TreeNode l, TreeNode r) {
            this.v = v;
            this.l = l;
            this.r = r;
        }
    }

    private static void doFind(TreeNode n, int target, int sum, List<TreeNode> result) {
        if (n == null) return;
        if (sum + n.v == target) {
            result.add(n);
        } else {
            doFind(n.l, target, sum + n.v, result);
            doFind(n.r, target, sum + n.v, result);
        }
    }

    public static List<TreeNode> find(TreeNode root, int target) {
        List<TreeNode> result = new ArrayList<>();
        doFind(root, target, 0, result);
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
            1,
            new TreeNode(10,
                new TreeNode(1,
                    new TreeNode(2, null, null),
                    null
                ),
                new TreeNode(0,
                    new TreeNode(1, null, null),
                    new TreeNode(5, null, null)
                )),
            new TreeNode(9,
                new TreeNode(2, null, null),
                null
            )
        );

        List<TreeNode> x = HasPathSum.find(root, 12);
        System.out.println(x.stream().map(n -> n.v).collect(Collectors.toList()));
    }
}
