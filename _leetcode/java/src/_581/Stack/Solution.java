package _581.Stack;

import java.util.Stack;

public class Solution {
    public int findUnsortedSubarray(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        // 找到左边的起始点
        int left = nums.length;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                left = Math.min(left, stack.pop());
            }
            stack.push(i);
        }

        stack.clear();
        // 找到右边的起始点
        int right = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i])
                right = Math.max(right, stack.pop());
            stack.push(i);
        }
        return right - left > 0 ? right - left + 1 : 0;
    }
}