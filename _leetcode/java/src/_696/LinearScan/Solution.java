package _696.LinearScan;

class Solution {
    public int countBinarySubstrings(String s) {
        int zeros = 0, ones = 0, res = 0;

        if (s.charAt(0) == '1') {
            ones++;
        } else {
            zeros++;
        }

        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i) == '1') {
                if (s.charAt(i - 1) == '1') {
                    ones++;
                } else {
                    ones = 1;
                }
                if (zeros >= ones) res++;
            } else if (s.charAt(i) == '0') {
                if (s.charAt(i - 1) == '0') {
                    zeros++;
                } else {
                    zeros = 1;
                }
                if (ones >= zeros) ++res;
            }
        }
        return res;
    }
}