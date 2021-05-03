package _617;

import structures.TreeNode;

class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return null;
        if (root1 == null || root2 == null) return root1 == null ? root2 : root1;

        root2.val = root1.val + root2.val;
        root2.left = mergeTrees(root1.left, root2.left);
        root2.right = mergeTrees(root1.right, root2.right);
        return root2;
    }
}