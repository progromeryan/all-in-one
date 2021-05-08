package _653;

import structures.TreeNode;

import java.util.*;


class Solution {

    HashSet<Integer> set;

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;
        set = new HashSet<>();
        return helper(root, k);
    }

    private boolean helper(TreeNode root, int k) {
        if (root == null) return false;
        if (set.contains(k - root.val)) return true;

        set.add(root.val);
        return helper(root.left, k) || helper(root.right, k);
    }
}