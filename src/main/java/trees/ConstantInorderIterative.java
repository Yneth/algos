package trees;

import java.util.ArrayList;
import java.util.List;

public class ConstantInorderIterative {
    // The direct implementation of an inorder traversal using recursion has 0(h) space
    // complexity, where h is the height of the tree. Recursion can be removed with an
    // explicit stack, but the space complexity remains0(h).
    // Write a non recursive program for computing the inorder traversal sequence for a
    // binary tree. Assume nodes have parent fields.

    // Solution:
    //        1
    //    2       3
    //  x   4   5   z
    //     y  c       a
    //                  b
    //
    // 1. go to the left most node, and add it
    // 2. if it has right node go to it  (4)
    //      repeat 1,2,3
    // 3. if the node is a leaf, add it
    //      if current node is the left node of the parent
    //          add parent, move to parents' right node if it has one
    //          otherwise go to the parent.parent.right node
    //      else
    //          if (isRight) return
    //          node = root.right
    //          isRight = true
    //        1 -> leftmost node -> x
    //        add x
    //        add 2
    //        move 4
    ///     without y
    //        add 4
    //        add 1
    //        move 3
    //        move 5
    //        add 5
    //        move null
    //   with y, z
    //        move y add y
    //        add 4
    //        add 1
    //        move 3
    //        move 5
    //        add 5 add 3
    //        move z
    //        add z
    //        move a add a
    //        move b add b

    static class TreeNode {

    }

    public List<Integer> compute(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        TreeNode iter = root;
        do {

        } while (iter != root);
        return results;
    }

}
