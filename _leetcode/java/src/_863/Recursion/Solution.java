package _863.Recursion;

import java.util.*;

import structures.*;

/**
 * 计算每个节点到target的距离
 */
class Solution {
    List<Integer> res = new ArrayList<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        helper(root, target, K);
        return res;
    }

    private int helper(TreeNode root, TreeNode target, int K) {
        if (root == null) return -1;
        // 找到了target
        // 那么要寻找距离target K的点
        if (root == target) {
            collect(target, K);
            return 0;
        }

        int l = helper(root.left, target, K);
        int r = helper(root.right, target, K);

        if (l >= 0) {
            // 那么当前节点就是解
            if (l == K - 1) {
                res.add(root.val);
            }

            collect(root.right, K - l - 2);
            return l + 1;
        }

        if (r >= 0) {
            if (r == K - 1) {
                res.add(root.val);
            }

            collect(root.left, K - r - 2);
            return r + 1;
        }

        return -1;
    }

    // 找到root的子节点,距离为d
    private void collect(TreeNode root, int d) {
        if (root == null || d < 0) return;
        if (d == 0) {
            res.add(root.val);
        }

        collect(root.left, d - 1);
        collect(root.right, d - 1);
    }
}