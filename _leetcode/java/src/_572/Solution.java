package _572;

import structures.TreeNode;

public class Solution {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (subRoot == null) return true;
        if (root == null) return false;
        if (root.val == subRoot.val) {
            if (helper(root, subRoot)) {
                return true;
            }
        }

        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean helper(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) return true;
        if (root == null || subRoot == null) return false;

        if (root.val != subRoot.val) return false;

        return helper(root.left, subRoot.left) && helper(root.right, subRoot.right);
    }
}
