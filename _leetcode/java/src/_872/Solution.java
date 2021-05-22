package _872;

import java.util.*;

import structures.*;

class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leaf1 = new ArrayList<>();
        List<Integer> leaf2 = new ArrayList<>();
        helper(root1, leaf1);
        helper(root2, leaf2);
        if (leaf1.size() != leaf2.size()) return false;
        for (int i = 0; i < leaf1.size(); i++) {
            if (leaf1.get(i) != leaf2.get(i)) return false;
        }

        return true;
    }

    private void helper(TreeNode root, List<Integer> res) {
        if (root == null) return;
        helper(root.left, res);
        if (root.left == null && root.right == null) {
            res.add(root.val);
            return;
        }
        helper(root.right, res);
    }
}