package _567.Dict;

public class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] s1map = new int[26];
        int[] s2map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            s1map[s1.charAt(i) - 'a']++;
            s2map[s2.charAt(i) - 'a']++;
        }

        if (matches(s1map, s2map)) return true;

        for (int i = 1; i <= s2.length() - s1.length(); i++) {
            int start = i - 1;
            int end = i + s1.length() - 1;

            s2map[s2.charAt(start) - 'a']--;
            s2map[s2.charAt(end) - 'a']++;

            if (matches(s1map, s2map)) return true;

        }

        return false;
    }

    // 比较string的第二种方法
    public boolean matches(int[] s1map, int[] s2map) {
        for (int i = 0; i < 26; i++) {
            if (s1map[i] != s2map[i]) return false;
        }
        return true;
    }
}
