package _785.BFS;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public boolean isBipartite(int[][] graph) {
        int[] colors = new int[graph.length];

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < graph.length; i++) {
            if (colors[i] == 0) {
                queue.offer(i);
                // 染色成1
                colors[i] = 1;

                while (!queue.isEmpty()) {
                    int curr = queue.poll();
                    for (int next : graph[curr]) {
                        // 相邻的点颜色相同
                        if (colors[next] == colors[curr]) return false;
                        // 相邻的点颜色不同
                        if (colors[next] != 0) continue;
                        // 染色
                        colors[next] = -colors[curr];
                        // 继续给相邻的点染色
                        queue.offer(next);
                    }
                }
            }
        }

        return true;
    }
}