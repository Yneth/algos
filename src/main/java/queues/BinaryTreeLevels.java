package queues;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevels {

    // Given a binary tree, return an array consisting of the keys at the same level. Keys
    // should appear in the order of the corresponding nodes' depths, breaking ties from left
    // to right
    //
    // Example:
    //   1
    // 2   3
    //4 5  7 8
    //
    // Result:
    // [[1], [2,3], [4,5,7,8]]
    //
    // Solution:
    // BreadthFirst

    // init queue
    // init result
    // add [root]
    // while (not empty queue)
    //   nodes = queue.toList()
    //   for n in nodes
    //     queue.add(n.left)
    //     queue.add(n.right)
    // return result
    // Test:
    // [1]
    // [2 3]

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<List<Integer>> treeLevels(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<TreeNode> levelNodes = new ArrayList<>();
            while (!queue.isEmpty()) levelNodes.add(queue.poll());

            List<Integer> level = new ArrayList<>();
            for (TreeNode node : levelNodes) {
                if (node == null) continue;
                level.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }
            if (!level.isEmpty()) result.add(level);
        }
        return result;
    }

    public static void main(String[] args) {
        BinaryTreeLevels sut = new BinaryTreeLevels();
        System.out.println(sut.treeLevels(
            new TreeNode(1,
                new TreeNode(2,
                    new TreeNode(4, null, null),
                    new TreeNode(5, null, null)
                ),
                new TreeNode(3,
                    new TreeNode(6, null, null),
                    new TreeNode(7, null, null)
                ))
        ));
        System.out.println(sut.treeLevels(new TreeNode(1, null, null)));
    }


}
