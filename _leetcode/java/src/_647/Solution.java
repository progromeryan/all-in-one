package _647;

class Solution {
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;

        boolean[][] dp = new boolean[s.length()][s.length()];
        int res = 0;

        for (int len = 1; len <= s.length(); len++) {
            for (int i = 0; i <= s.length() - len; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 2 || dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        res++;
                    }
                }
            }
        }

        return res;
    }
}