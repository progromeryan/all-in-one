package _542.BFS;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                } else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                for (int[] dir : dirs) {
                    int x = curr[0] + dir[0];
                    int y = curr[1] + dir[1];
                    if (x < 0 || x >= m || y < 0 || y >= n) continue;
                    if (visited[x][y]) continue;
                    if (matrix[x][y] != 0) {
                        matrix[x][y] = Math.min(matrix[x][y], step);
                        visited[x][y] = true;
                        queue.offer(new int[]{x, y});
                    }
                }
            }
            step++;
        }
        return matrix;
    }
}
