package _650;

class Solution {
    public int minSteps(int n) {
        int[] dp = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            int min = i;
            for (int j = 2; j < i; j++) {
                int remain = i - j;
                if (remain % j == 0) {
                    min = Math.min(min, dp[j] + 1 + (remain / j));
                }
            }

            dp[i] = min;
        }

        return dp[n];
    }
}