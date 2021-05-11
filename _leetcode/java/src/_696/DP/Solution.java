package _696.DP;

// Time Limit Exceeded
class Solution {
    public int countBinarySubstrings(String s) {
        int res = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];

        for (int len = 1; len <= s.length(); len++) {
            for (int i = 0; i + len <= s.length(); i++) {
                int j = i + len - 1;
                char m = s.charAt(i);
                char n = s.charAt(j);
                if (i == j || (j - i) % 2 == 0 || m == n) {
                    continue;
                }

                if (j - i == 1 || (dp[i + 1][j - 1] && m == s.charAt(i + 1) && n == s.charAt(j - 1))) {
                    dp[i][j] = true;
                    res++;
                }
            }
        }

        return res;
    }
}