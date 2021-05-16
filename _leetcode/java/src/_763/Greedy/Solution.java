package _763.Greedy;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> partitionLabels(String s) {
        int[] last = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            last[s.charAt(i) - 'a'] = i;
        }

        // 如果当前字符串中有这个字符,那么至少也要到这个index才能包括所有已存在的字符
        int minIndex = 0;
        int start = 0;
        List<Integer> res = new ArrayList();

        for (int i = 0; i < s.length(); ++i) {
            minIndex = Math.max(minIndex, last[s.charAt(i) - 'a']);
            if (i == minIndex) {
                res.add(i - start + 1);
                start = i + 1;
            }
        }
        return res;
    }
}