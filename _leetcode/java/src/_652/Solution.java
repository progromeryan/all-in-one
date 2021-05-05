package _652;

import structures.TreeNode;

import java.util.*;

class Solution {
    Map<String, TreeNode> trees;
    Map<String, Integer> count;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        trees = new HashMap();
        count = new HashMap();
        List<TreeNode> res = new ArrayList();
        helper(root);

        for (String key : count.keySet()) {
            if (count.get(key) >= 2) {
                res.add(trees.get(key));
            }
        }

        return res;
    }

    public void helper(TreeNode root) {
        if (root == null) return;
        helper(root.left);
        String encoded = encodeTreeNode(root);
        trees.put(encoded, root);
        count.put(encoded, count.getOrDefault(encoded, 0) + 1);
        helper(root.right);
    }

    private String encodeTreeNode(TreeNode root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr == null) {
                sb.append("null ");
                continue;
            }

            int val = curr.val;
            sb.append(val + " ");
            queue.offer(curr.left);
            queue.offer(curr.right);
        }

        return sb.toString();
    }
}