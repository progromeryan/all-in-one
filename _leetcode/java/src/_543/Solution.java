package _543;

import structures.TreeNode;

class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        int left = getHeight(root.left);
        int right = getHeight(root.right);

        return Math.max((left + right), Math.max(diameterOfBinaryTree(root.left), diameterOfBinaryTree(root.right)));
    }

    private int getHeight(TreeNode root) {
        if (root == null) return 0;

        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
}
