package _856.Stack;

import java.util.*;

/**
 * ( ( ) ( ( ) ) )
 * [0, 0] after parsing (
 * [0, 0, 0] after (
 * [0, 1] after )
 * [0, 1, 0] after (
 * [0, 1, 0, 0] after (
 * [0, 1, 1] after )
 * [0, 3] after )
 * [6] after )
 */
class Solution {
    public int scoreOfParentheses(String S) {
        Stack<Integer> stack = new Stack();
        stack.push(0);

        for (char c : S.toCharArray()) {
            if (c == '(') {
                stack.push(0);
            } else {
                int v = stack.pop();
                int w = stack.pop();
                stack.push(w + Math.max(2 * v, 1));
            }
        }

        return stack.pop();
    }
}