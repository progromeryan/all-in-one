package _200.UnionFind;

import structures.UnionFind;

public class Solution {
    public int numIslands(char[][] grid) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        UnionFind unionFind = new UnionFind(grid);

        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
                    for (int[] dir : dirs) {
                        int x = i + dir[0];
                        int y = j + dir[1];
                        if (x < 0 || x >= m || y < 0 || y >= n) continue;
                        if (grid[x][y] == '0') continue;
                        unionFind.union(i * n + j, x * n + y);
                    }
                }
            }
        }

        return unionFind.getGroupNum();
    }
}

