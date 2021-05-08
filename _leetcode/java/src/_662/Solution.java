package _662;

import structures.*;

import java.util.*;


// 子节点在下一行的位置是2*n, 2*n + 1
class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        int maxWidth = 0;

        queue.offer(new Pair<>(root, 0));

        while (!queue.isEmpty()) {
            int size = queue.size();
            int headIndex = -1;
            int tailIndex = -1;
            for (int i = 0; i < size; i++) {
                Pair<TreeNode, Integer> element = queue.poll();
                TreeNode node = element.getKey();

                int index = element.getValue();
                if (i == 0) {
                    headIndex = index;
                }

                tailIndex = index;

                if (node.left != null) {
                    queue.offer(new Pair<>(node.left, 2 * element.getValue()));
                }

                if (node.right != null) {
                    queue.offer(new Pair<>(node.right, 2 * element.getValue() + 1));
                }
            }

            maxWidth = Math.max(maxWidth, tailIndex - headIndex + 1);
        }

        return maxWidth;
    }
}