package _688;

class Solution {
    public double knightProbability(int n, int k, int row, int column) {
        double[][] dp = new double[n][n];
        dp[row][column] = 1.0;

        int[][] dirs = {
                {1, 2}, {-1, -2}, {1, -2}, {-1, 2},
                {2, 1}, {-2, -1}, {2, -1}, {-2, 1}
        };

        // 每次走一步
        while (k != 0) {
            // 当前这一步的dp
            double[][] temp = new double[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int[] dir : dirs) {
                        int x = i + dir[0];
                        int y = j + dir[1];

                        if (x < 0 || y < 0 || x >= n || y >= n) continue;
                        // 从上一步走到当前的位置有多少种可能
                        // 每一步都在累加结果
                        temp[x][y] += dp[i][j] / 8.0;
                    }
                }
            }
            // 更新dp
            dp = temp;
            k--;
        }

        double res = 0.0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res += dp[i][j];
            }
        }

        return res;
    }
}