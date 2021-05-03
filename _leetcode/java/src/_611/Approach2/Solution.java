package _611.Approach2;

import java.util.Arrays;

class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = nums.length - 1; i >= 2; i--) {
            int j = i - 1;
            int end = 0;
            while (end < j) {
                if (nums[j] + nums[end] > nums[i]) {
                    res += j - end;
                    j--;
                } else {
                    end++;
                }
            }
        }
        return res;
    }
}