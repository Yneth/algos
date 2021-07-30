package trees;

public class FindLCAWithParents {
    // Given two nodes in a binary tree, design an algorithm that computes their LCA.
    // Assume that each node has a parent pointer
    //
    // Solution:
    //  get the depth of each node
    //  init iterators for each node
    //  make sure iterators get even in the depth
    //  then iterate both checking if they are equal
    //
    //       1
    //   2         3
    // 4       8
    //          9
    //
    // LCA(4,9)
    //   depth(4) = 3
    //   depth(9) = 4
    //  iter(4) start at node(4)
    //  iter(9) start at node(8)
    //  4 != 8
    //  2 != 3
    //  1 == 1 OK

    static class TreeNode {
        int v;
        TreeNode l, r, p;
        public TreeNode(int v, TreeNode p, TreeNode l, TreeNode r) {
            this.v = v;
            this.p = p;
            this.l = l;
            this.r = r;
        }
    }

    private static int depth(TreeNode n) {
        TreeNode i = n;
        int depth = 0;
        while (i != null) {
            depth++;
            i = i.p;
        }
        return depth;
    }

    public static TreeNode find(TreeNode root, TreeNode l, TreeNode r) {
        int lDepth = depth(l), rDepth = depth(r);

        TreeNode lIter = l;
        while (lDepth > rDepth) {
            lIter = lIter.p;
            lDepth--;
        }

        TreeNode rIter = r;
        while (rDepth > lDepth) {
            rIter = rIter.p;
            rDepth--;
        }

        while (rIter != null && lIter != null) {
            if (rIter == lIter) return rIter;
            rIter = rIter.p;
            lIter = lIter.p;
        }
        return null;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, null, null, null);
        TreeNode left1 = new TreeNode(2, root, null, null);
        TreeNode right1 = new TreeNode(3, root, null, null);

        TreeNode n20 = new TreeNode(4, left1, null, null);
        TreeNode n21 = new TreeNode(8, right1, null, null);
        TreeNode n30 = new TreeNode(9, n21, null, null);

        System.out.println(
            FindLCAWithParents.find(
                root,
                root,
                n30
            ).v
        );
    }


}
