package _863.Graph;

import java.util.*;

import structures.*;

/**
 * tree -> graph
 */
class Solution {
    HashMap<Integer, List<Integer>> map = new HashMap<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        // 建图
        buildGraph(null, root);
        List<Integer> res = new ArrayList<>();

        HashSet<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(target.val);
        visited.add(target.val);

        int k = 0;
        while (!queue.isEmpty() && k <= K) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                if (k == K) {
                    res.add(curr);
                }
                if (!map.containsKey(curr)) continue;
                for (int next : map.get(curr)) {
                    if (visited.contains(next)) continue;
                    queue.offer(next);
                    visited.add(next);
                }
            }

            k++;
        }
        return res;
    }

    private void buildGraph(TreeNode parent, TreeNode child) {
        if (parent != null) {
            if (!map.containsKey(parent.val)) {
                map.put(parent.val, new ArrayList<>());
            }

            map.get(parent.val).add(child.val);

            if (!map.containsKey(child.val)) {
                map.put(child.val, new ArrayList<>());
            }

            map.get(child.val).add(parent.val);
        }

        if (child.left != null) buildGraph(child, child.left);
        if (child.right != null) buildGraph(child, child.right);
    }
}