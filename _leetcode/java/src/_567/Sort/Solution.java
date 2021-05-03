package _567.Sort;

import java.util.Arrays;

public class Solution {
    public boolean checkInclusion(String s1, String s2) {
        String s1s = helper(s1);

        for (int i = 0; i <= s2.length() - s1s.length(); i++) {
            String sub = helper(s2.substring(i, i + s1s.length()));
            if (sub.equals(s1s)) {
                return true;
            }
        }
        return false;
    }

    private String helper(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }
}
