package trees;

import java.util.*;

public class TreeFromTraversalData {
    // Many different binary trees yield the same sequence of keys in an inorder, preorder,
    // or postorder traversal. However, given an inorder traversal and one of any two other
    // traversal orders of a binary tree, there exists a unique binary tree that yields those
    // orders, assuming each node holds a distinct key. For example, the unique binary
    // tree whose inorder traversal sequence is (F,B,A,E,H,C,D,I,G) and whose preorder
    // traversal sequence is (H,B,F,E,A,C,D,G,I) is given in Figure 10.5 on the following
    // page.
    // Given an inorder traversal sequence and a preorder traversal sequence of a binary
    // tree write a program to reconstruct the tree. Assume each node has a unique key.
    //
    // Example:
    //               H
    //          B          C
    //        F   E          D
    //           A             G
    //                        I
    //
    // inorder: left root right
    // preorder: root left right
    //
    // preorder gives us root and its' left node
    //        H
    //    B
    //  Bs' left child would be F from preorder data
    //        Fs' left child would be null because it is the first node in inorder data
    //        Bs' right node would be E
    //          Es' left node would be A  A is before E in inorder data & preoder data has it as a  left node of E
    //  C is the right child of H by preorder data
    //    D is the irght child of C by inorder data
    // pseudocode:
    //   reconstruct(preorder, inorder)
    //     root = preorder[0]
    //     root.left = reconstruct(preorder[1:], inorder[:root])
    //     root.right = reconstrcut(preorder[1:], inorder[root:])
    //   reconstruct(n, po, io)
    //     if
    //

    static class TreeNode {
        int val;
        TreeNode left, right;

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //      1
    //  2       3
    //   4    5
    //
    // in: 2 4 1 5 3
    // pre: 1 2 4 3 5

    private static TreeNode doBuild(
        List<Integer> preorder,
        int preorderStart, int preorderEnd,
        int inorderStart, int inorderEnd,
        Map<Integer, Integer> inorderIdx
    ) {
        if (preorderStart >= preorderEnd  || inorderStart >= inorderEnd) return null;

        int inorderRootIdx = inorderIdx.get(preorder.get(preorderStart));
        int leftTreeSize = inorderRootIdx - inorderStart;

        return new TreeNode(
            preorder.get(preorderStart),
            doBuild(
                preorder, preorderStart + 1, preorderEnd - 1 + leftTreeSize,
                inorderStart, inorderRootIdx, inorderIdx
            ),
            doBuild(
                preorder, preorderStart + 1 + leftTreeSize, preorderEnd,
                inorderRootIdx + 1, inorderEnd, inorderIdx
            )
        );
    }

    public static TreeNode build(List<Integer> preorder, List<Integer> inorder) {
        Map<Integer, Integer> inorderIdx = new HashMap<>();
        for (int i = 0; i < inorder.size(); i++) {
            inorderIdx.put(inorder.get(i), i);
        }
        return doBuild(
            preorder, 0, preorder.size(),
            0, inorder.size(), inorderIdx
        );
    }

    // in: 2 4 1 5 3
    // pre: 1 2 4 3 5

    private static void printTree(TreeNode root)  {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<TreeNode> ns = new ArrayList<>();
            while (!queue.isEmpty()) {
                ns.add(queue.remove());
            }

            int i = 0;
            for (TreeNode n : ns) {
                if (n == null) {
                    System.out.print(" ");
                    if (i % 2 == 1) System.out.print(" ");
                    continue;
                }
                queue.add(n.left);
                queue.add(n.right);
                System.out.print("  ");
                if (i % 2 == 1) System.out.print(" ");
                System.out.print(n.val);
                i++;
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        TreeNode root = TreeFromTraversalData.build(
            Arrays.asList(1, 2, 4, 3, 5),
            Arrays.asList(2, 4, 1, 5, 3)
        );
        System.out.println(root.right.left.val);
        System.out.println(root.left.right.val);
        System.out.println(root);
        printTree(root);
    }

}
