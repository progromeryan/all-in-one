package _545;

import structures.TreeNode;

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    List<Integer> res = new ArrayList<>();

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if (root == null) return res;
        res.add(root.val);
        getLeft(root.left);
        getBottom(root.left);
        getBottom(root.right);
        getRight(root.right);

        return res;
    }

    private void getLeft(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return;
        res.add(root.val);
        if (root.left != null) {
            getLeft(root.left);
        } else {
            getLeft(root.right);
        }
    }

    private void getRight(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return;
        if (root.right != null) {
            getRight(root.right);
        } else {
            getRight(root.left);
        }

        res.add(root.val);
    }

    private void getBottom(TreeNode root) {
        if (root == null) return;
        getBottom(root.left);
        if (root.left == null && root.right == null) {
            res.add(root.val);
        }
        getBottom(root.right);
    }
}