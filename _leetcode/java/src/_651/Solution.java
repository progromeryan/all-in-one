package _651;

class Solution {
    public int maxA(int N) {
        int[] dp = new int[N + 1];

        for (int i = 1; i <= N; ++i) {
            // 如果一直输入A, 至少也能在这一步得到这么多个A
            dp[i] = i;
            for (int j = 1; j < i - 1; ++j) {
                // 复制一次然后全部粘贴
                dp[i] = Math.max(dp[i], dp[j] * (i - j - 1));
            }
        }

        return dp[N];
    }
}