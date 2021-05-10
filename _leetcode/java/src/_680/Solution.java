package _680;

class Solution {
    public boolean validPalindrome(String s) {
        if (s == null || s.length() == 0) return true;
        if (helper(s)) return true;

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            char x = s.charAt(left);
            char y = s.charAt(right);

            if (x == y) {
                left++;
                right--;
                continue;
            }
            return helper(s.substring(left + 1, right + 1)) || helper(s.substring(left, right));
        }

        return true;
    }

    private boolean helper(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            char x = s.charAt(left);
            char y = s.charAt(right);
            if (x != y) return false;
            left++;
            right--;
        }

        return true;
    }
}