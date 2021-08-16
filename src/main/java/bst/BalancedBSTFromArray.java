package bst;


import java.util.*;

public class BalancedBSTFromArray {
    // Given a sorted array, the number of BSTs that can be built on the entriesin the array
    // grows enormously with its size. Some of these trees are skewed, and are closer to
    //     lists; others are more balanced. See Figure 15.3 on Page 263 for an example.
    // How would you build a BST of minimum possible height from a sorted array?
    //
    // Examples:
    // 1 2 3 4 5 6
    // 0 1 2 3 4 5
    //   arr is even, so I need to decide
    //   let n/2 be the mid for odd and even cases
    //
    // mid = 5 + 0 / 2 = 2
    //   arr[2]=3 - root
    //       0 + 1 / 2 =  arr[0]=1 - left node
    //       arr[5+3/2]=arr[4] - right node
    //
    // solution:
    // can be implemented in two ways
    // via recursion
    //  or via stack (harder)
    //
    // i'll use recursion
    // recipe:
    // - start (0,n)
    // - pick mid as the root
    // - left node = (s,mid)
    // - right node = (mid,e)

    static class TreeNode {
        int val;
        TreeNode left, right;
        public TreeNode(int val, TreeNode l, TreeNode r) {
            this.val = val;
            this.left = l;
            this.right = r;
        }
    }

    private static TreeNode from(int[] arr, int l, int h) {
        if (l >= h) return null;

        int mid = (l + h) >>> 1;
        return new TreeNode(
            arr[mid],
            from(arr, l, mid),
            from(arr, mid + 1, h)
        );
    }

    public static TreeNode from(int[] arr) {
        return from(arr, 0, arr.length);
    }

    public static void print(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            List<TreeNode> ns = new ArrayList<>();
            while (!q.isEmpty()) {
                ns.add(q.remove());
            }
            for (TreeNode n : ns) {
                if (n == null) continue;
                System.out.print(n.val + "   ");
                q.add(n.left);
                q.add(n.right);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        print(from(new int[] {1, 2, 3, 4, 5, 6, 7}));
        print(from(new int[] {1, 2, 3, 4, 5, 6}));
        print(from(new int[] {1}));
        print(from(new int[] {}));
    }
}
