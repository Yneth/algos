package trees;

public class FindLCA {

    // Any two nodesin a binary tree have a common ancestor, namely the root. The lowest
    // common ancestor (LCA) of any two nodes in a binary tree is the node furthest from
    // the root that is an ancestor of both nodes.
    // Design an algorithm for computing the LCA of two nodesin a binary tree in which
    // nodes do not have a parent field.

    //    1
    // 2     3
    //4 5   6  7
    //
    // LCA(4,5) = 2
    // LCA(2,3) = 1
    // LCA(4,3) = 1
    //
    // Solution:
    //  my idea is to have recursive function with Node return type
    //  if it is null, then node was not found
    //  otherwise we found one of the two node we were looking for
    //
    //  the first node which would have the left and right nodes not null would be LCA
    //
    // Test:
    //   LCA(2,7)
    //     1      2 and 7
    //        ret 2
    //              3
    //                 6
    //                    ret 7
    //
    //   LCA(4,5)
    //     1
    //          2   4 and 5
    //              ret 4
    //                      ret 5
    // Recursive solution:
    // LCA(n, l, r):
    //    if n == null: return null
    //    if n == l: return l
    //    if n == r: return r
    //
    //    lca_l = LCA(n.left, l, r)
    //    lca_r = LCA(n.right, l, r)
    //
    //    if (lca_l != null && lca_r == null) return lca_l
    //    if (lca_l == null && lca_r != null) return lca_r
    //    if (lca_l != null && lca_r != null) return Terminate()
    //    return null

    static class TreeNode {
        int val;
        TreeNode l, r;

        public TreeNode(int v, TreeNode l, TreeNode r) {
            this.val = v;
            this.l = l;
            this.r = r;
        }
    }

    static class LCAResult {
        boolean terminate;
        TreeNode node;

        static LCAResult leaf(TreeNode n) {
            LCAResult leaf = new LCAResult();
            leaf.node = n;
            return leaf;
        }

        static LCAResult result(TreeNode n) {
            LCAResult leaf = new LCAResult();
            leaf.node = n;
            leaf.terminate = true;
            return leaf;
        }
    }

    private static LCAResult doFind(TreeNode n, int l, int r) {
        if (n == null) return null;
        if (n.val == l) return LCAResult.leaf(n);
        if (n.val == r) return LCAResult.leaf(n);

        LCAResult left = doFind(n.l, l, r);
        LCAResult right = doFind(n.r, l, r);

        if (left != null && right != null) return LCAResult.result(n);
        if (left != null) return left;
        if (right != null) return right;
        return null;
    }

    public static TreeNode find(TreeNode root, int l, int r) {
        return doFind(root, l, r).node;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,
            new TreeNode(2,
                new TreeNode(4, null, null),
                new TreeNode(5, null, null)
            ),
            new TreeNode(3,
                new TreeNode(6,
                    new TreeNode(8,
                        new TreeNode(9, null, null), null), null),
                new TreeNode(7, null, null)
            )
        );
        System.out.println(FindLCA.find(root, 2, 3).val);
        System.out.println(FindLCA.find(root, 4, 5).val);
        System.out.println(FindLCA.find(root, 7, 5).val);
        System.out.println(FindLCA.find(root, 6, 7).val);
        System.out.println(FindLCA.find(root, 9, 7).val);
    }


}
