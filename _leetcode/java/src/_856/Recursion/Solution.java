package _856.Recursion;

public class Solution {
    public int scoreOfParentheses(String s) {
        return helper(s, 0, s.length() - 1);
    }

    private int helper(String s, int start, int end) {
        if (end - start == 1) return 1; // ()
        int count = 0;

        for (int i = start; i < end; i++) {
            if (s.charAt(i) == '(') count++;
            if (s.charAt(i) == ')') count--;
            if (count == 0) {
                // helper("(A)(B)") = helper("(A)") + helper("(B)")
                return helper(s, start, i) + helper(s, i + 1, end);
            }
        }

        // helper("(A)") = 2 * helper("A")
        return 2 * helper(s, start + 1, end - 1);
    }
}
