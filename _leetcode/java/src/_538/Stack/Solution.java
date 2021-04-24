package _538.Stack;

import structures.TreeNode;

import java.util.Stack;

class Solution {
    public TreeNode convertBST(TreeNode root) {
        int sum = 0;
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<>();

        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.add(node);
                node = node.right;
            }

            node = stack.pop();
            sum += node.val;
            node.val = sum;

            node = node.left;
        }

        return root;
    }
}
