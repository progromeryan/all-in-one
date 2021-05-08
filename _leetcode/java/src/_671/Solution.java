package _671;

import structures.TreeNode;

class Solution {
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) return -1;
        if (root.left != null && root.right != null) {
            if (root.left.val == root.val && root.right.val == root.val) {
                int left = findSecondMinimumValue(root.left);
                int right = findSecondMinimumValue(root.right);
                if (left != -1 && right != -1) {
                    return Math.min(left, right);
                }
                return left == -1 ? right : left;
            }

            if (root.right.val > root.val) {
                int left = findSecondMinimumValue(root.left);
                if (left == -1) return root.right.val;
                return Math.min(left, root.right.val);
            }

            if (root.left.val > root.val) {
                int right = findSecondMinimumValue(root.right);
                if (right == -1) return root.left.val;
                return Math.min(right, root.left.val);
            }
        }
        return -1;
    }
}