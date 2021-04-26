package _547.BFS;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int countComponents(int n, int[][] edges) {
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        int count = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int curr = queue.poll();
                    visited[curr] = true;
                    for (int[] edge : edges) {
                        int x = edge[0];
                        int y = edge[1];
                        if (x == curr && !visited[y]) {
                            queue.offer(y);
                        }

                        if (y == curr && !visited[x]) {
                            queue.offer(x);
                        }
                    }
                }
                count++;
            }
        }
        return count;
    }
}