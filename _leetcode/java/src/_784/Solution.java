package _784;

import java.util.*;

class Solution {
    public List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList<>();
        helper(res, s, 0, "");
        return res;
    }

    private void helper(List<String> res, String s, int start, String curr) {
        if (start > s.length()) return;
        if (curr.length() == s.length()) {
            res.add(curr);
            return;
        }

        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                curr += c;
                if (curr.length() == s.length()) {
                    res.add(curr);
                    return;
                }
                continue;
            } else {
                helper(res, s, i + 1, curr + Character.toLowerCase(c));
                helper(res, s, i + 1, curr + Character.toUpperCase(c));
            }
        }
    }
}
