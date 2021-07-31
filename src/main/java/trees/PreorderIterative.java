package trees;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class PreorderIterative {
    // Write a program which takes asinput a binary tree and performs a preorder traversal
    // of the tree. Do not use recursion. Nodes do not contain parent references.
    //
    // Solution:
    // init stack with iter(root)
    // init result
    // while stack
    //   n = stack.pop()
    //   result.add(n.value)
    //   stack.push(n.right)
    //   stack.push(n.left)
    // Test:
    //
    //        1
    //     2     3
    //    4 5   6 7
    //
    //  1 2 4 5 3 6 7

    static class TreeNode {
        int value;
        TreeNode left, right;
        public TreeNode(int v, TreeNode l, TreeNode r) {
            this.value = v;
            this.left = l;
            this.right = r;
        }
    }


    public static List<Integer> compute(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                result.add(node.value);
                stack.push(node.right);
                stack.push(node.left);
            }
        }
        return result;
    }

    public static void main(String... args) {
        TreeNode root = new TreeNode(
            1,
            new TreeNode(2,
                null,
                new TreeNode(5, null, null)
            ),
            new TreeNode(3,
                new TreeNode(6, null, null),
                null
            )
        );
        System.out.println(PreorderIterative.compute(root));
    }
}
