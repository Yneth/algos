package trees;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class InorderIterative {

    // Write a program which takes as input a binary tree and performs an inorder traversal
    // of the tree. Do not use recursion. Nodes do not contain parent references.

    // Example:
    //       1
    //   2      3
    // 4 5     6 7
    //
    // 4 2 5 1 6 3 7
    //
    // left v right
    // Solution:
    // use stack
    //  init with iterable root
    //  while stack
    //    pop node
    //    push iterable right
    //    push terminal node
    //    push iterable left
    //
    //
    // push i(3)
    // push t(1)
    // push i(2)

    // push i(4)
    // push t(2)
    // push i(5)
    // // push i(4 left)
    // push t(4)
    // push i(4 right)

    // pop i(4 left) skip
    // pop 4  add to result
    // pop i(4 right) skip
    //
    // pop t(2) add to result
    //
    //
    // pop i(5)
    // push t(5)
    // pop 5 add to result

    static class TreeNode {
        int v;
        TreeNode l, r;

        public TreeNode(int v, TreeNode l, TreeNode r) {
            this.v = v;
            this.l = l;
            this.r = r;
        }
    }

    static enum NodeType {
        ITERATOR, TERMINAL;
    }

    static class NodeWrapper {
        TreeNode node;
        NodeType type;

        public static NodeWrapper iter(TreeNode node) {
            NodeWrapper n = new NodeWrapper();
            n.type = NodeType.ITERATOR;
            n.node = node;
            return n;
        }

        public static NodeWrapper terminal(TreeNode node) {
            NodeWrapper w = iter(node);
            w.type = NodeType.TERMINAL;
            return w;
        }
    }

    public static List<Integer> compute(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<NodeWrapper> stack = new LinkedList<>();
        stack.push(NodeWrapper.iter(root));
        while (!stack.isEmpty()) {
            NodeWrapper n = stack.pop();
            if (n.node == null) continue;

            if (n.type == NodeType.TERMINAL) {
                result.add(n.node.v);
            } else {
                stack.push(NodeWrapper.iter(n.node.r));
                stack.push(NodeWrapper.terminal(n.node));
                stack.push(NodeWrapper.iter(n.node.l));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
            1,
            new TreeNode(2,
                new TreeNode(4, null, new TreeNode(100, null, null)),
                new TreeNode(5, null, null)),
            new TreeNode(3,
                new TreeNode(6, null, null),
                new TreeNode(7, null, null))
        );
        System.out.println(InorderIterative.compute(root));
    }

}
