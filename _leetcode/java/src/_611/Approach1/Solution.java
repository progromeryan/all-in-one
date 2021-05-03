package _611.Approach1;

import java.util.Arrays;

class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] == 0) continue;
            for (int j = i + 1; j < nums.length - 1; j++) {
                int end = j + 1;
                int twoSum = nums[i] + nums[j];
                while (end < nums.length && twoSum > nums[end]) {
                    res++;
                    end++;
                }
            }
        }
        return res;
    }
}