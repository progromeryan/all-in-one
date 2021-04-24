package _538;

import structures.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    List<Integer> sum = new ArrayList<>();
    int count = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root == null) return null;

        helper(root);
        for (int i = sum.size() - 2; i >= 0; i--) {
            sum.set(i, sum.get(i + 1) + sum.get(i));
        }
        update(root);
        return root;
    }

    private void helper(TreeNode root) {
        if (root == null) return;
        helper(root.left);
        sum.add(root.val);
        helper(root.right);
    }

    private void update(TreeNode root) {
        if (root == null) return;
        update(root.left);
        root.val = sum.get(count++);
        update(root.right);
    }
}
