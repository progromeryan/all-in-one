package _735;

import java.util.*;

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for (int as : asteroids) {
            if (as < 0) {
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < Math.abs(as)) {
                    stack.pop();
                }

                if (stack.isEmpty()) {
                    stack.push(as);
                } else {
                    if (stack.peek() > 0 && stack.peek() == Math.abs(as)) {
                        stack.pop();
                    } else if (stack.peek() < 0) {
                        stack.push(as);
                    }
                }
            } else {
                stack.push(as);
            }
        }

        int[] res = new int[stack.size()];

        int i = res.length - 1;
        while (!stack.isEmpty()) {
            res[i--] = stack.pop();
        }

        return res;
    }
}