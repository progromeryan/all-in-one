package _542.DP;

public class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Integer.MAX_VALUE - 100000;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0)
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                if (j > 0)
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
            }
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i < m - 1)
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);
                if (j < n - 1)
                    dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + 1);
            }
        }

        return dp;
    }
}

