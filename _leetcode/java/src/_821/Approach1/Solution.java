package _821.Approach1;

import java.util.*;

import structures.*;

class Solution {
    public int[] shortestToChar(String s, char c) {
        int[] res = new int[s.length()];
        Arrays.fill(res, Integer.MAX_VALUE);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                res[i] = 0;
                helper(res, i);
            }
        }

        return res;
    }

    private void helper(int[] res, int index) {
        int step = 1;
        int left = index - 1;
        while (left >= 0 && res[left] > step) {
            res[left] = step;
            left--;
            step++;
        }

        int right = index + 1;
        step = 1;
        while (right < res.length && res[right] > step) {
            res[right] = step;
            step++;
            right++;
        }
    }
}