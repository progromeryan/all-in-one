package _694;

import java.util.*;

class Solution {
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int numDistinctIslands(int[][] grid) {
        Set<String> set = new HashSet<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder s = new StringBuilder();
                    set.add(helper(grid, i, j, i, j, s));
                }
            }
        }

        return set.size();
    }

    private String helper(int[][] grid, int i0, int j0, int i, int j, StringBuilder s) {
        grid[i][j] = 0;

        s.append(i - i0);
        s.append("_");
        s.append(j - j0);

        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];

            if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 0) {
                continue;
            }

            helper(grid, i0, j0, x, y, s);
        }

        return s.toString();
    }
}
