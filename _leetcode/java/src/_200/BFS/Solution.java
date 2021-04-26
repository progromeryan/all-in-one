package _200.BFS;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int res = 0;

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    // 每次处理掉所有相邻的1
                    grid[i][j] = '0';
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i, j});
                    while (!queue.isEmpty()) {
                        int[] curr = queue.poll();
                        for (int[] dir : dirs) {
                            int x = curr[0] + dir[0];
                            int y = curr[1] + dir[1];
                            if (x < 0 || x >= m || y < 0 || y >= n) continue;
                            if (grid[x][y] != '1') continue;
                            queue.offer(new int[]{x, y});
                            grid[x][y] = '0';
                        }
                    }
                }
            }
        }
        return res;
    }
}

