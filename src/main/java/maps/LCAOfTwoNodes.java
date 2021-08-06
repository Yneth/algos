package maps;

import java.util.HashSet;
import java.util.Set;

public class LCAOfTwoNodes {

    // Problem10.4 on Page 157 is concerned with computing the LCA in a binary tree with
    // parent pointersin time proportional to the height of the tree. The algorithm presented
    // in Solution 10.4 on Page 157 entailstraversing all the way to the root even if the nodes
    // whose LCA is being computed are very close to their LCA.
    // Design an algorithm for computing the LCA of two nodes in a binary tree. The
    // algorithm's time complexity should depend only on the distance from the nodes to
    // the LCA.
    //
    // Solution:
    //  initialize a set
    //  iterate both nodes
    //       at each step
    //        check if it contains nodes we are currently at
    //          if yes return contained
    //          else populate the set
    //
    // Test:
    //            1
    //   2             3
    // 5      6
    //      8     9
    //          10
    //
    // LCA(5, 10) = 2 and LCA(3, 5) = 1
    //
    //
    //  LCA(5,10)
    //   -  [2, 9]
    //   - [2,9,1,6]
    //   return 2
    // LCA(3, 5)
    //   - [2, 1]
    //   - return 1

    static class TreeNode {
        int v;
        TreeNode left, right, parent;
        public TreeNode(int v, TreeNode l, TreeNode r) {
            this.v = v;
            this.left = l;
            this.right = r;
        }
    }

    public static TreeNode find(TreeNode l, TreeNode r) {
        Set<TreeNode> visited = new HashSet<>();
        TreeNode lIter = l;
        TreeNode rIter = r;
        while (lIter != null || rIter != null) {
            if (visited.contains(lIter)) return lIter;
            if (visited.contains(rIter)) return rIter;

            if (lIter != null) visited.add(lIter);
            if (rIter != null) visited.add(rIter);

            if (lIter != null) lIter = lIter.parent;
            if (rIter != null) rIter = rIter.parent;
        }
        return null;
    }

    public static void main(String[] args) {
        TreeNode l10000000 = new TreeNode(9, null, null);
        TreeNode l00010000 = new TreeNode(8, null, null);
        TreeNode l00100000 = new TreeNode(7, null, null);

        TreeNode l1000 = new TreeNode(6, l10000000, null);
        TreeNode l0100 = new TreeNode(5, l00100000, l00010000);
        TreeNode l0001 = new TreeNode(4, null, null);
        TreeNode l01 = new TreeNode(3, null, l0001);
        TreeNode l10 = new TreeNode(2, l1000, l0100);
        TreeNode root = new TreeNode(1, l10, l01);

        l10000000.parent = l1000;
        l00010000.parent = l0100;
        l00100000.parent = l0100;
        l1000.parent = l10;
        l0100.parent = l10;
        l10.parent = root;
        l01.parent = root;
        System.out.println(find(l10000000, l01).v);
        System.out.println(find(l10000000, l0100).v);
        System.out.println(find(l10000000, root).v);
    }

}
